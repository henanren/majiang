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
import RaisedButton from "material-ui/RaisedButton";
import Commons from "../commons/index";
import {Card, CardActions, CardHeader, CardText} from "material-ui/Card";
import {Toolbar, ToolbarGroup, ToolbarSeparator, ToolbarTitle} from "material-ui/Toolbar";
import NavFirstPage from "material-ui/svg-icons/navigation/first-page";
import NavLastPage from "material-ui/svg-icons/navigation/last-page";

const styles = {
    buttons: {
        margin: "10px 0 10px 24px",
    },
    numberButtons: {
        margin: "10px 0 10px 24px",
        minWidth: "auto"
    }
};

type State = {
    open: boolean
};

type Props = {
    pageInfo:{
        page:Number,
        pageSize:Number,
        count:count
    },
    url:String,
};

const style = {
    margin: 12,
};

export default class PageBar extends Component {
    commons: Commons = new Commons().bind(this);

    state: State = {
        open: true,
        loading: false
    };
    props: Props;

    static defaultProps = {
        pageInfo: null,
        url: ""
    };

    handleToggle = () => this.setState({open: !this.state.open});

    goto(path) {
        console.log(path);
        browserHistory.push(path);
    }

    render() {
        let state = this.state;
        let props = this.props;
        let pageInfo = props.pageInfo || {
                page: 0,
                pageSize: 0,
                count: 0
            };

        let url = props.url.replace(":pageSize", pageInfo.pageSize);
        let maxPage = Math.ceil(pageInfo.count / pageInfo.pageSize);
        return (
            <Toolbar>
                <ToolbarGroup firstChild={true}>
                </ToolbarGroup>
                <ToolbarGroup>
                    <ToolbarTitle
                        text={`第${pageInfo.page}页，共${maxPage}页，${pageInfo.count} 条`}/>
                    <ToolbarSeparator />
                    {
                        pageInfo.page > 1 ? <RaisedButton
                                icon={<NavFirstPage />}
                                style={styles.buttons}
                                onTouchTap={()=>{this.goto(url.replace(":page", 1))}}
                            /> : null
                    }
                    {
                        (() => {
                            let list = [];
                            let i;
                            for (i = 1; i < pageInfo.page; i++) {
                                ((i) => {
                                    list.push(<RaisedButton
                                        key={`${i}`}
                                        label={`${i}`}
                                        style={styles.numberButtons}
                                        onTouchTap={()=>{this.goto(url.replace(":page", i))}}
                                    />);
                                })(i);
                            }
                            return list;
                        })()
                    }
                    {
                        <RaisedButton
                            key={`${pageInfo.page}`}
                            label={`${pageInfo.page}`}
                            style={styles.numberButtons}
                            disabled={true}
                        />
                    }
                    {
                        (() => {
                            let list = [];
                            let i;
                            for (i = pageInfo.page + 1; i <= Math.min(maxPage, pageInfo.page + 3); i++) {
                                ((i) => {
                                    list.push(<RaisedButton
                                        key={`${i}`}
                                        label={`${i}`}
                                        style={styles.numberButtons}
                                        onTouchTap={()=>{this.goto(url.replace(":page", i))}}
                                    />);
                                })(i);
                            }
                            return list;
                        })()
                    }

                    {
                        pageInfo.page < maxPage ? <RaisedButton
                                icon={<NavLastPage />}
                                style={styles.buttons}
                                onTouchTap={()=>{this.goto(url.replace(":page", maxPage))}}
                            /> : null
                    }
                </ToolbarGroup>
            </Toolbar>
        );
    }

    onMenuTap = () => {
        this.handleToggle();
    }
}
