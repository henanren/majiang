import React from "react";
import {browserHistory} from "react-router";
import Dialog from "material-ui/Dialog";
import TextField from "material-ui/TextField";
import RaisedButton from "material-ui/RaisedButton";
import Commons, {ProgressView} from "../commons/index";
import Apis, {LoginForm, LoginTokenModel} from "../../admin-js-sdk/index";
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
export default class Login extends React.Component {
    commons: Commons = new Commons().bind(this);
    state = {
        open: true,
        name: "",
        password: "",
        loading: false
    };

    handleOpen = () => {
        this.setState({open: true});
    };

    handleClose = () => {
        this.setState({open: false});
    };

    onSubmit = () => {
        this.setState({loading: true});
        this.commons.apis.accountApi.login(
            LoginForm.form(this.state.name, this.state.password)
        ).then((m: LoginTokenModel) => {
            localStorage.setItem('token', m.token);
            Apis.setToken(m.token);
            this.handleClose();
            browserHistory.goBack();
        }).default(() => {
            this.setState({loading: false});
        });
    };

    render() {
        let state = this.state;
        const actions = [
            <RaisedButton
                label="取消"
                primary={true}
                onTouchTap={this.handleClose}
                style={styles.buttons}
            />,
            <RaisedButton
                label="登录"
                secondary={true}
                keyboardFocused={true}
                onTouchTap={this.onSubmit}
                style={styles.buttons}
            />,
        ];

        return (
            <Dialog
                title="登录"
                actions={actions}
                modal={false}
                open={this.state.open}
                onRequestClose={this.handleClose}
                autoScrollBodyContent={true}
                contentStyle={{width:400}}
                style={{textAlign:"center"}}
            >
                <Divider/>
                <TextField
                    floatingLabelText="输入用户名"
                    value={state.name}
                    onChange={(_,name)=>{this.setState({name})}}
                    style={styles.inputs}
                />
                <TextField
                    floatingLabelText="输入密码"
                    type="password"
                    value={state.password}
                    onChange={(_,password)=>{this.setState({password})}}
                    style={styles.inputs}
                />
                <ProgressView isVisible={state.loading}/>
            </Dialog>
        );
    }
}