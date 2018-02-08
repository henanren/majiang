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
import {PageModel, RoomModel} from "../../admin-js-sdk/index";
import RaisedButton from "material-ui/RaisedButton";
import Divider from 'material-ui/Divider';

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
        this.commons.apis.roomApi.list(props.params.page || 1, props.params.pageSize || 20).then((pageModel: PageModel) => {
            this.setState({
                pageModel,
                loading: false
            });
        }).default(()=>{
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
                    title="房间"
                />
                <Divider/>
                <CardText style={{padding:0}}>
                    <Table>
                        <TableHeader
                            displaySelectAll={false}
                            adjustForCheckbox={false}
                        >
                            <TableRow>
                                <TableHeaderColumn style={{width:60}}>ID</TableHeaderColumn>
                                <TableHeaderColumn style={{width:120}}>用户</TableHeaderColumn>
                                <TableHeaderColumn style={{width:120}}>时间</TableHeaderColumn>
                                {/*<TableHeaderColumn>操作</TableHeaderColumn>*/}
                            </TableRow>
                        </TableHeader>
                        <TableBody stripedRows={true}
                                   showRowHover={true}
                                   displayRowCheckbox={false}
                        >
                            {
                                state.pageModel ? state.pageModel.list.map((r: RoomModel, i) => {
                                        return <TableRow key={`row-${r.id}`}>
                                            <TableRowColumn style={{width:60}}>{r.roomCheckId}</TableRowColumn>
                                            <TableRowColumn style={{width:120}}>
                                                用户1:{r.userName0}<br/>
                                                用户2:{r.userName1}<br/>
                                                用户3:{r.userName2}<br/>
                                                用户4:{r.userName3}
                                            </TableRowColumn>
                                            <TableRowColumn style={{width:120}}>
                                                开始:{r.startDate}<br/>
                                                结束:{r.endDate}
                                            </TableRowColumn>
                                            {/*<TableRowColumn>*/}
                                                {/*<RaisedButton label="删除" primary={true} style={style}*/}
                                                              {/*onTouchTap={()=>this.onDel(r.id)}/>*/}
                                                {/*<RaisedButton label="充值" secondary={true} style={style}*/}
                                                              {/*onTouchTap={()=>this.onPay(r.id)}/>*/}
                                            {/*</TableRowColumn>*/}
                                        </TableRow>;
                                    }) : null
                            }

                        </TableBody>
                    </Table>
                    <PageBar pageInfo={state.pageModel} url="/room/:page/:pageSize.html"/>
                </CardText>
                <ProgressView isVisible={state.loading}/>
            </Card>
        );
    }

    onDel = (id) => {
        this.update(this.props);
    };

    onPay = (id) => {
        browserHistory.push("/pay/" + id+".html");
    };

    onMenuTap = () => {
        this.handleToggle();
    };
}
