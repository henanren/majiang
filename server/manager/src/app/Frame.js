import React, {Component} from "react";
import {Router, Route, Link, IndexRoute, browserHistory} from "react-router";
import AppBar from "material-ui/AppBar";
import Drawer from "material-ui/Drawer";
import {List, ListItem} from "material-ui/List";
import ContentDrafts from "material-ui/svg-icons/content/drafts";
import ContentSend from "material-ui/svg-icons/content/send";
import {Table, TableBody, TableHeader, TableHeaderColumn, TableRow, TableRowColumn} from "material-ui/Table";
import Login from "../pages/Login";

const styles = {
    container: {
        textAlign: 'center',
        paddingTop: 200,
    },
};

type State = {
    open: boolean,
    login: boolean
};

const style = {
    margin: 12,
};

export default class Frame extends Component {
    contextTypes: {
        router: React.PropTypes.object
    };
    state: State = {
        open: true
    };

    handleToggle = () => this.setState({open: !this.state.open});

    goto(path) {
        browserHistory.push(path);
    }

    componentDidMount() {

    }

    render() {
        let state = this.state;
        return (
            <div>
                <AppBar
                    title="麻将管理平台"
                    iconClassNameRight="muidocs-icon-navigation-expand-more"
                    onLeftIconButtonTouchTap={this.onMenuTap}
                />

                <Drawer width={300} openSecondary={false} open={this.state.open} docked={true}
                        onRequestChange={(open) => this.setState({open})}>
                    <AppBar title="麻将管理平台" onLeftIconButtonTouchTap={this.onMenuTap}/>
                    <div>
                        <List>
                            <ListItem primaryText="用户管理" leftIcon={<ContentSend />}
                                      onTouchTap={()=>{this.goto("/user.html")}}/>
                            <ListItem primaryText="用户充值" leftIcon={<ContentSend />}
                                      onTouchTap={()=>{this.goto("/pay.html")}}
                            />
                            <ListItem primaryText="房间管理" leftIcon={<ContentDrafts />}
                                      onTouchTap={()=>{this.goto("/room.html")}}
                            />

                            <ListItem primaryText="系统设置" leftIcon={<ContentDrafts />}
                                      onTouchTap={()=>{this.goto("/setting.html")}}
                            />
                            {/*<ListItem primaryText="房卡销售统计" leftIcon={<ContentSend />}/>*/}
                            {/*<ListItem primaryText="系统日志" leftIcon={<ContentDrafts />}/>*/}
                            {/*<ListItem primaryText="系统设置" leftIcon={<ContentDrafts />}/>*/}
                        </List>
                    </div>
                </Drawer>
                <div style={{
                    paddingLeft:this.state.open?320:20,
                    paddingRight:20,
                    paddingTop:20,
                    paddingButtom:20
                }}>
                    {this.props.children}
                </div>
            </div>
        );
    }

    onMenuTap = () => {
        this.handleToggle();
    }
}
