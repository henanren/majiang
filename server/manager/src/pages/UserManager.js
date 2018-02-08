import React, {Component} from "react";
import {Router, Route, Link, IndexRoute, browserHistory} from "react-router";
import {List, ListItem} from "material-ui/List";
import {
    Table,
    TableBody,
    TableHeader,
    TableHeaderColumn,
    TableRow,
    TableRowColumn,
    TableFooter
} from "material-ui/Table";
import Commons, {ProgressView, PageBar} from "../commons/index";
import {Card, CardActions, CardHeader, CardText} from "material-ui/Card";
import {Toolbar, ToolbarGroup, ToolbarSeparator, ToolbarTitle} from "material-ui/Toolbar";
import {PageModel, UserModel} from "../../admin-js-sdk/index";
import RaisedButton from "material-ui/RaisedButton";
import Divider from "material-ui/Divider";
import SelectField from "material-ui/SelectField";
import MenuItem from "material-ui/MenuItem";
const Immutable = require("seamless-immutable").static;


const styles = {
    container: {
        textAlign: 'center',
        paddingTop: 200,
    }, buttons: {
        fontSize: 12,
    }
};

type State = {
    open: boolean,
    loading: boolean,
    payUserId: Number,
    pageModel: PageModel,
};

type Props = {
    params:{
        page:String,
        pageSize: String
    },
};

const style = {
    margin: 12,
};

export default class UserManager extends Component {
    commons: Commons = new Commons().bind(this);

    state: State = {
        open: true,
        loading: false,
        pageModel: null,
        payUserId: 0
    };

    props: Props;
    static defaultProps = {
        params: {}
    };

    handleToggle = () => this.setState({open: !this.state.open});

    componentDidMount() {
        //登录服务器
        this.update(this.props);
    }

    componentWillReceiveProps(nextProps) {
        this.update(nextProps);
    }

    update(props) {
        this.setState({
            loading: true
        });
        this.commons.apis.userApi.list(props.params.page || 1, props.params.pageSize || 20).then((pageModel: PageModel) => {
            this.setState({
                pageModel: Immutable(pageModel),
                loading: false
            });
        }).default(() => {
            this.setState({
                loading: false
            });
        });
    }


    render() {
        let state = this.state;
        return (
            <Card>
                <CardHeader
                    title="用户管理"
                />
                <Divider/>
                <CardText style={{padding:0}}>
                    <Table selectable={false}>
                        <TableHeader
                            displaySelectAll={false}
                            adjustForCheckbox={false}
                        >
                            <TableRow>
                                <TableHeaderColumn style={{width:60,textAlign:'center'}}>ID</TableHeaderColumn>
                                <TableHeaderColumn style={{width:60,textAlign:'center'}}>用户名</TableHeaderColumn>
                                <TableHeaderColumn style={{width:100,textAlign:'center'}}>房卡&代理级别</TableHeaderColumn>
                                <TableHeaderColumn style={{width:120, textAlign:'center'}}>注册时间</TableHeaderColumn>
                                <TableHeaderColumn style={{textAlign:'center'}}>操作</TableHeaderColumn>
                            </TableRow>
                        </TableHeader>
                        <TableBody
                            displayRowCheckbox={false}
                        >
                            {
                                state.pageModel ? state.pageModel.list.map((r: UserModel, i) => {
                                        let id = r.id;
                                        let name = r.name;
                                        let gold = r.gold;
                                        let historyGold = r.historyGold;
                                        let createDate = r.createDate;
                                        let level = r.level;

                                        return <TableRow key={`row-${id}`}>
                                            <TableRowColumn style={{width:60, textAlign:'center'}}>{id}</TableRowColumn>
                                            <TableRowColumn
                                                style={{width:60, textAlign:'center'}}>{name}</TableRowColumn>
                                            <TableRowColumn style={{width:100}}>
                                                剩　　余:{gold}<br/>
                                                充值历史:{historyGold}<br/>
                                                <div>
                                                    <SelectField
                                                        floatingLabelText="代理级别"
                                                        value={level}
                                                        onChange={this.selectLevel(i)}
                                                        style={{width:100, textAlign:'left'}}
                                                    >
                                                        <MenuItem value={0} primaryText="非代理"/>
                                                        <MenuItem value={1} primaryText="1级代理"/>
                                                        <MenuItem value={2} primaryText="2级代理"/>
                                                        <MenuItem value={3} primaryText="3级代理"/>
                                                    </SelectField>
                                                </div>
                                            </TableRowColumn>
                                            <TableRowColumn
                                                style={{width:120, textAlign:'center'}}>{createDate}</TableRowColumn>
                                            <TableRowColumn style={{textAlign:'center'}}>
                                                <RaisedButton label="删除" primary={true} style={style}
                                                              onTouchTap={()=>this.onDel(r.id)}/><br/>
                                                <RaisedButton label="充值" secondary={true} style={style}
                                                              onTouchTap={()=>this.onPay(r.id)}/>
                                            </TableRowColumn>
                                        </TableRow>;
                                    }) : null
                            }

                        </TableBody>
                    </Table>
                    <PageBar pageInfo={state.pageModel} url="/user/:page/:pageSize.html"/>
                </CardText>
                <ProgressView isVisible={state.loading}/>
            </Card>
        );
    }

    selectLevel = (index) => {
        return (_1, _2, level) => {
            let pageModel: PageModel = this.state.pageModel;
            this.setState({
                pageModel: Immutable.setIn(pageModel, ["list", index, "level"], level)
            });
            let user: UserModel = pageModel.list[index];
            this.commons.apis.userApi.changelevel(user.id, level);
        }
    };

    onDel = (id) => {
        this.update(this.props);
    };

    onPay = (id) => {
        browserHistory.push(`/pay/${id}.html`);
    };

    onMenuTap = () => {
        this.handleToggle();
    };
}
