import Apis from '../../admin-js-sdk/index';



function __clearTimeout(commons: Commons, intervalId: Number) {
    const index = commons._timeIds.indexOf(intervalId);
    if (index > -1) {
        commons._timeIds.splice(index, 1);
    }
}
function __clearAll(commons: Commons) {
    commons._timeIds.forEach((id) => {
        commons.clearTimeout(id);
    });
    commons.apis.stopAll();
}

export default class Commons {
    apis: Apis = new Apis();
    _timeIds: Number[] = [];

    constructor() {

    }

    bind(obj) {
        let self = this;
        let componentWillUnmount = function () {
            console.log("Test.componentWillUnmount");
            __clearAll(self);
        };

        if (obj.componentWillUnmount) {
            let oldComponentWillUnmount = obj.componentWillUnmount.bind(obj);
            obj.componentWillUnmount = function () {
                oldComponentWillUnmount();
                componentWillUnmount();
            };
        } else {
            obj.componentWillUnmount = function () {
                componentWillUnmount();
            };
        }
        return this;
    }

    setTimeout(code, delay) {
        const timeId: Number = setTimeout(
            () => {
                code();
                __clearTimeout(this,timeId);
            }, delay
        );
        this._timeIds.push(timeId);
        return timeId;
    }

    clearTimeout(intervalId: Number) {
        clearTimeout(this, intervalId);
        __clearTimeout(timeId);
    }
}
