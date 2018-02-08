import React from "react";
import {render} from "react-dom";
import injectTapEventPlugin from "react-tap-event-plugin";
import Main from "./Main"; // Our custom react component
import Apis, {ApiUtils} from "../../admin-js-sdk/index";
import {Router, Route, Link, IndexRoute, browserHistory} from "react-router";

console.log(window._clientAddress);

if (window._clientAddress) {
    Apis.init(window._clientAddress, window._clientVersion);
} else {
    // Apis.init("http://gd.paipaimj.com/","v1");
    Apis.init("http://127.0.0.1:8080/", "v1");
}


var token = localStorage.getItem('token');
if (token) {
    Apis.setToken(token);
} else {
    browserHistory.push("/login.html");
}
// Needed for onTouchTap
// http://stackoverflow.com/a/34015469/988941
// http://stackoverflow.com/a/34015469/988941
injectTapEventPlugin();

// Render the main app react component into the app div.
// For more details see: https://facebook.github.io/react/docs/top-level-api.html#react.render
render(<Main />, document.getElementById('app'));
