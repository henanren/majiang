import React from "react";
import TextField from "material-ui/TextField";
import RaisedButton from "material-ui/RaisedButton";
import Commons, {ProgressView} from "../commons/index";
import {PayForm, UserModel} from "../../admin-js-sdk/index";
import {Card, CardActions, CardHeader, CardText} from "material-ui/Card";
import Dialog from "material-ui/Dialog";
import FlatButton from "material-ui/FlatButton";
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
export default class Login extends React.Component {
    commons: Commons = new Commons().bind(this);
    state = {
        open: false,
        userId: 0,
        gold: 0,
        userModel: null,
        loading: false
    };

    handleOpen = () => {
        this.setState({open: true});
    };

    componentDidMount() {
        this.setState({
            userId: this.props.params.userId
        });
    }

    handleClose = () => {
        this.setState({open: false});
    };

    onSubmit = () => {
        this.setState({loading: true});
        console.log(PayForm);
        this.commons.apis.userApi.pay(
            PayForm.form(this.state.userId, this.state.gold)
        ).then((userModel: userModel) => {
            this.setState({loading: false, userModel});
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
                label="充值"
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
                        title="用户充值"
                    />
                    <CardText >
                        <TextField
                            floatingLabelText="用户id"
                            value={state.userId}
                            onChange={(_,userId)=>{this.setState({userId})}}
                            style={styles.inputs}
                        />
                        <br/>
                        <TextField
                            floatingLabelText="充值金额"
                            value={state.gold}
                            onChange={(_,gold)=>{this.setState({gold})}}
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
        var userModel: UserModel = this.state.userModel;
        if (userModel == null) {
            return null;
        }
        return (
            <div>
                <Dialog
                    actions={actions}
                    modal={false}
                    open={this.state.open}
                    onRequestClose={this.handleClose}
                >
                    为用户:{userModel.name},充值成功<br/>
                    用户剩余房卡:{userModel.gold}<br/>
                </Dialog>
            </div>
        );
    }
}