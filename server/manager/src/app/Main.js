import React, {Component} from "react";
import {deepOrange500} from "material-ui/styles/colors";
import FlatButton from "material-ui/FlatButton";
import getMuiTheme from "material-ui/styles/getMuiTheme";
import {Router, Route, Link, IndexRoute, browserHistory} from "react-router";
import RaisedButton from "material-ui/RaisedButton";
import Dialog from "material-ui/Dialog";
import MuiThemeProvider from "material-ui/styles/MuiThemeProvider";
import DatePicker from "material-ui/DatePicker";

import UserManager from "../pages/UserManager";
import RoomManager from "../pages/RoomManager";
import Pay from "../pages/Pay";
import Login from "../pages/Login";
import Setting from "../pages/Setting";


import Frame from "./Frame";


const styles = {
    container: {
        textAlign: 'center',
        paddingTop: 200,
    },
};

const muiTheme = getMuiTheme({
    palette: {
        accent1Color: deepOrange500,
    },
});

const About = React.createClass({
    render() {
        return <h3>About</h3>
    }
});

const Inbox = React.createClass({
    render() {
        return (
            <div>
                <h2>Inbox</h2>
                {this.props.children || "Welcome to your Inbox"}
            </div>
        )
    }
});

const Message = React.createClass({
    render() {
        return <h3>Message {this.props.params.id}</h3>
    }
});

const Dashboard = React.createClass({
    render() {
        return <div>
        </div>;
    }
});


class Main extends Component {
    constructor(props, context) {
        super(props, context);

        this.handleRequestClose = this.handleRequestClose.bind(this);
        this.handleTouchTap = this.handleTouchTap.bind(this);

        this.state = {
            open: false,
        };
    }

    handleRequestClose() {
        console.log("handleRequestClose");
        this.setState({
            open: false,
        });
    }

    handleTouchTap() {
        console.log("handleTouchTap");
        this.setState({
            open: true,
        });
    }

    render() {
        const standardActions = (
            <FlatButton
                label="Ok"
                primary={true}
                onTouchTap={this.handleRequestClose}
            />
        );

        return <MuiThemeProvider muiTheme={muiTheme}>
            <Router history={browserHistory}>
                <Route path="/" component={Frame}>
                    <IndexRoute component={Dashboard}/>
                    <Route path="about.html" component={About}/>

                    <Route path="login.html" component={Login}/>
                    <Route path="setting.html" component={Setting}/>
                    <Route path="user.html" component={UserManager}/>
                    <Route path="user/:page/:pageSize.html" component={UserManager}/>

                    <Route path="room.html" component={RoomManager}/>
                    <Route path="room/:page/:pageSize.html" component={RoomManager}/>

                    <Route path="pay.html" component={Pay}/>
                    <Route path="pay/:userId.html" component={Pay}/>

                    <Route path="inbox.html" component={Inbox}>
                        <Route path="messages/:id.html" component={Message}/>
                    </Route>
                </Route>
            </Router>
        </MuiThemeProvider>;

        // return (
        //
        //         <div style={styles.container}>
        //             <Dialog
        //                 open={this.state.open}
        //                 title="Super Secret Password"
        //                 actions={standardActions}
        //                 onRequestClose={this.handleRequestClose}
        //             >
        //                 1-2-3-4-5
        //             </Dialog>
        //             <h1>Material-UI</h1>
        //             <h2>example project</h2>
        //             <RaisedButton
        //                 label="Super Secret Password"
        //                 secondary={true}
        //                 onTouchTap={this.handleTouchTap}
        //             />
        //             <div>
        //                 <DatePicker hintText="Portrait Dialog"/>
        //                 <DatePicker hintText="Landscape Dialog" mode="landscape"/>
        //                 <DatePicker hintText="Dialog Disabled" disabled={true}/>
        //             </div>
        //         </div>
        //     </MuiThemeProvider>
        // );
    }
}

export default Main;
