'use strict';

import ApiUtils from "./ApiUtils";

class AbstractApi {
    _xhrHandler:(xhr:XMLHttpRequest)=> void;

    constructor() {

    }

    _initNet(xhrHandler:(xhr:XMLHttpRequest)=> void) {
        this._xhrHandler = xhrHandler;
    }

    _request(method:String, url:String, pathVars:Object, formObject:Object):Promise {
        return ApiUtils.requestApi(method, url, pathVars, formObject, this._xhrHandler);
    }
}

export default AbstractApi;