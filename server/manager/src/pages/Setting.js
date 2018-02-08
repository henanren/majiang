import React from "react";
import TextField from "material-ui/TextField";
import RaisedButton from "material-ui/RaisedButton";
import Commons, {ProgressView} from "../commons/index";
import {SettingModel, SettingForm} from "../../admin-js-sdk/index";
import {Card, CardActions, CardHeader, CardText} from "material-ui/Card";
import Dialog from "material-ui/Dialog";
import FlatButton from "material-ui/FlatButton";
import Divider from "material-ui/Divider";

const styles = {
    buttons: {
        marginLeft: '20px',
    },
    inputs: {
        // width: "100%",
    },
};

/**
 * Dialog with action buttons. The actions are passed in as an array of React objects,
 * in this example [FlatButtons](/#/components/flat-button).
 *
 * You can also close this dialog by clicking outside the dialog, or with the 'Esc' key.
 */
export default class Setting extends React.Component {
    commons: Commons = new Commons().bind(this);
    state = {
        open: false,
        notice: "",
        radio: "",
        payInfo: "",
        agreement: "",
        initGold: 0,
    };


    componentDidMount() {
        //登录服务器
        this.update(this.props);
    }

    componentWillReceiveProps(nextProps) {
        this.update(nextProps);
    }

    update(props) {
        this.setState({
            loading: true,
        });
        this.commons.apis.settingApi.get().then((setting: SettingModel) => {
            this.setState({
                notice: setting.notice,
                radio: setting.radio,
                payInfo: setting.payInfo,
                agreement: setting.agreement,
                initGold: setting.initGold,
                loading: false
            });
        }).default(() => {
            this.setState({
                loading: false
            });
        });
    }

    handleOpen = () => {
        this.setState({open: true});
    };

    handleClose = () => {
        this.setState({open: false});
    };

    onSubmit = () => {
        this.setState({loading: true});
        let state = this.state;
        this.commons.apis.settingApi.change(
            SettingForm.of(state)
        ).then((setting: Boolean) => {
            this.setState({
                notice: setting.notice,
                radio: setting.radio,
                payInfo: setting.payInfo,
                agreement: setting.agreement,
                initGold: setting.initGold,
                loading: false
            });
            this.handleOpen();
        }).default(() => {
            this.setState({loading: false});
        });
    };

    render() {
        let state = this.state;
        const actions = [

            <RaisedButton
                key="2"
                label="修改设置"
                secondary={true}
                keyboardFocused={true}
                onTouchTap={this.onSubmit}
                style={styles.buttons}
            />,
        ];

        return (
            <div>
                <Card>
                    <CardHeader
                        title="系统设置"
                    />
                    <Divider/>
                    <CardText >
                        <TextField
                            floatingLabelText="新注册送房卡数:"
                            value={state.initGold}
                            onChange={(_,initGold)=>{this.setState({initGold})}}
                            style={styles.inputs}
                        />
                        <br/>
                        <TextField
                            floatingLabelText="跑马灯公告"
                            multiLine={true}
                            fullWidth={true}
                            rows={2}
                            rowsMax={3}
                            value={state.radio == null?"":state.radio}
                            onChange={(_,radio)=>{this.setState({radio})}}
                            style={styles.inputs}
                        />
                        <br/>
                        <TextField
                            floatingLabelText="健康游戏公告"
                            multiLine={true}
                            fullWidth={true}
                            rows={5}
                            rowsMax={5}
                            value={state.notice == null?"":state.notice}
                            onChange={(_,notice)=>{this.setState({notice})}}
                            style={styles.inputs}
                        />
                        <br/>
                        <TextField
                            floatingLabelText="充值提示"
                            multiLine={true}
                            fullWidth={true}
                            rows={5}
                            rowsMax={5}
                            value={state.payInfo == null?"":state.payInfo}
                            onChange={(_,payInfo)=>{this.setState({payInfo})}}
                            style={styles.inputs}
                        />
                        <br/>
                        <TextField
                            floatingLabelText="用户协议"
                            multiLine={true}
                            rows={5}
                            rowsMax={10}
                            fullWidth={true}
                            value={state.agreement == null?"":state.agreement}
                            onChange={(_,agreement)=>{this.setState({agreement})}}
                            style={styles.inputs}
                        />
                    </CardText>
                    <CardActions>
                        {actions}
                    </CardActions>
                </Card>
                <ProgressView isVisible={state.loading}/>
                {
                    this.renderDialog()
                }
            </div>
        );
    }

    renderDialog() {
        const actions = [
            <FlatButton
                label="确定"
                primary={true}
                onTouchTap={this.handleClose}
            />,
        ];
        return (
            <div>
                <Dialog
                    actions={actions}
                    modal={false}
                    open={this.state.open}
                    onRequestClose={this.handleClose}
                >
                    修改成功
                </Dialog>
            </div>
        );
    }
}