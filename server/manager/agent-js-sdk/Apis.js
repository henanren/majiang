'use strict';

import AgentAccountApi  from './api/AgentAccountApi';
import ApiUtils from "./ApiUtils";

class Apis {
    agentAccountApi:AgentAccountApi;
    _xhrArray:XMLHttpRequest[];

    static init(apiUrl:String, apiVersion:String){
        ApiUtils.apiUrl = apiUrl;
        ApiUtils.apiVersion = apiVersion;
    }

    static setToken(token:String){
        ApiUtils.setToken(token);
    }

    constructor() {
        this._xhrArray = [];
        this.agentAccountApi = new AgentAccountApi();
        this.agentAccountApi._initNet(this._xhrHandler.bind(this));
    }

    _xhrHandler(xhr:XMLHttpRequest) {
        this._xhrArray.push(xhr);
        xhr.loadend = ()=> {
            this._clearXhr(xhr);
        };
    }

    _clearXhr(xhr:XMLHttpRequest) {
        var index = this._xhrArray.indexOf(xhr);
        if (index > -1) {
            this._xhrArray.splice(index, 1);
        }
    }

    stopAll() {
        for (var i = 0; i < this._xhrArray.length; i++) {
            this._xhrArray[i].abort();
        }
        this._xhrArray.length = 0;
    }
}


export default Apis;