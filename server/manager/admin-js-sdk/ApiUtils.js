'use strict';
import { browserHistory} from "react-router";
// /**请求成功*/
// public static final int SUCCESS = 0;
// /**参数校验错误*/
// public static final int VALIDATOR = 1;
// /**服务器错误*/
// public static final int ERROR = 2;
// /**需要登录*/
// public static final int ACCOUNT_ERROR = -1;
// /**客户端异常*/
// public static final int CLIENT_ERROR = -2;
// /**客户取消*/
// public static final int CLIENT_CANCEL = -3;
// import {Alert} from "react-native";


const CLIENT_ERROR = -3;
const ERROR = 2;

const NAMES_PATTERN: RegExp = /\{([^\}]+?)\}/g;

// Promise.prototype.done = function (onFulfilled, onRejected) {
//     this.then(onFulfilled, onRejected)
//         .catch(function (reason) {
//             // 抛出一个全局错误
//             setTimeout(() => { throw reason }, 0);
//         });
// };


function alertMsg(msg: Object) {
    alert(msg.toString());
    // alert("错误", msg.toString(), [{text: "确定"}]);
    //jQuery.showMessage(msg.toString())
}

// class NetPromise extends Promise{
//     constructor(...args) {
//         super(...args);
//     }
//     done(){
//         alert("done");
//     }
// }
function handlerDefault() {
    if (Promise.prototype.default) {
        return;
    }
    Promise.prototype.default = function (callback) {
        return this.catch(function (data) {
            console.log("默认错误处理!:", data);
            handler(data);
            if (callback) {
                return callback(data);
            }
            function handler(data) {
                if (data && data.status) {
                    if (data.status == -1) {
                        console.log("未登陆");
                        browserHistory.push("/login.html");
                        localStorage.clear();
                    } else {
                        if (data.status == 1) {
                            let msgMap = data["msgMap"];
                            console.log(msgMap);
                            let sb = "";
                            Object.keys(msgMap).forEach((key) => {
                                sb += msgMap[key];
                                sb += "\n";
                            });
                            if (sb == null || sb.length == 0) {
                                alertMsg(data["msg"]);
                            } else {
                                alertMsg(sb);
                            }
                        } else {
                            alertMsg(data["msg"]);
                        }
                    }
                }
                if (data instanceof Error) {
                    setTimeout(
                        () => {
                            throw data;
                        }, 0
                    );
                }
            }
        });
    };
}

class ApiUtils {
    // static apiUrl:String = "http://" + window.location.host + "/";
    // static apiUrl:String = "http://www.tashenghuo.com.cn/";
    static apiUrl: String = "http://localhost:8080/";
    // static apiUrl:String = "http://192.168.0.205:8080/";
    // static apiUrl:String = "http://192.168.0.205:3000/";
    static apiVersion: String = "v0";
    static _token;

    static init(apiUrl: String, apiVersion: String) {
        ApiUtils.apiUrl = apiUrl;
        ApiUtils.apiVersion = apiVersion;
    }

    static setToken(token: String) {
        ApiUtils._token = token;
    }

    static requestApi(method: String, url: String, pathVars: Object,
                      formObject: Object, handler: (xhr: XMLHttpRequest)=> void, isHandlerCatch: Boolean): Promise {
        if (pathVars) {
            // console.log("pathVars:", pathVars);
            url = url.replace(NAMES_PATTERN, function (_, key) {
                return encodeURIComponent(pathVars[key]);
            });
            // console.log("pathVars to:", url);
        }
        handlerDefault();
        url = ApiUtils.apiUrl + ApiUtils.apiVersion + "/" + url + ".json";
        return ApiUtils.request(method, url, formObject, handler).catch((client: XMLHttpRequest): Promise => {
            if (client instanceof Error) {
                return Promise.reject(client);
            } else if (client.status > 0) {
                return Promise.reject({status: ERROR, msg: "服务器错误::" + client.statusText || client.responseText});
            } else {
                return Promise.reject({status: CLIENT_ERROR, msg: "客户端错误:" + client.statusText || client.responseText});
            }
        }).then((responseText: String): Promise => {
            try {
                console.log("responseText:", url, ",responseText", responseText);
                const data: Object = JSON.parse(responseText);
                if (data.status == 0) {
                    return Promise.resolve(data["data"]);
                } else if (data.status == -1) {
                    return Promise.reject(data);
                } else {
                    return Promise.reject(data);
                }
            } catch (e) {
                console.log("requestApi error", e);
                return Promise.reject({status: CLIENT_ERROR, msg: "客户端错误:" + e.toString()});
            }
        });
    }

    static request(method, url, data, handler: (xhr: XMLHttpRequest)=> void): Promise {
        method = method || "get";

        return new Promise(function (resolve, reject) {
            console.log("request:", method, url, data);
            let client: XMLHttpRequest = new XMLHttpRequest();
            if (client.overrideMimeType) {
                client.overrideMimeType("text/html");
            }
            let uri = url;
            let requestData = null;


            if (data) {
                if ((method === 'POST' || method === 'PUT')) {
                    requestData = encodeDate(data);
                } else {
                    uri += '?';
                    uri += encodeDate(data);
                }
            }

            client.open(method, uri);

            if (ApiUtils._token) {
                client.setRequestHeader("accountToken", ApiUtils._token);
            }

            client.onload = function () {
                if (client.status >= 200 && client.status < 300) {
                    console.log("this.responseText", substr(client.responseText));
                    resolve(client.responseText);
                } else {
                    console.log("request error", client.status, substr(client.statusText || client.responseText));
                    reject(client)
                }
            };
            client.onerror = function () {
                console.log("request error", client.status, client.statusText);
                reject(client);
            };

            // client.withCredentials = true;

            handler(client);
            if (requestData) {
                client.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;");
                client.send(requestData);
            } else {
                client.send();
            }

            function substr(str) {
                if (str) {
                    str = str.toString();
                    return str.substring(0, Math.min(100, str.length));
                }
                return str;
            }

            function encodeDate(data) {
                let str = "";
                let argcount = 0;

                // console.log("表单编码data:", data);

                Object.keys(data).forEach((key) => {
                    if (argcount++) {
                        str += '&';
                    }
                    const value = data[key];
                    if (Array.isArray(value)) {
                        value.forEach((value) => {
                            if (argcount++) {
                                str += '&';
                            }
                            const isHasValue = value !== null && typeof(value) != "undefined";
                            str += encodeURIComponent(key) + '=' + ( isHasValue ? encodeURIComponent(value.toString()) : "");
                        })
                    } else {
                        const isHasValue = value !== null && typeof(value) != "undefined";
                        str += encodeURIComponent(key) + '=' + ( isHasValue ? encodeURIComponent(value.toString()) : "");
                    }
                });
                return str;
            }
        });
    }
}

export default ApiUtils;
