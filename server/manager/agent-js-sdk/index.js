'use strict';

import AgentLoginForm from './form/AgentLoginForm';
import AgentLoginTokenModel from './model/AgentLoginTokenModel';
import Apis from "./Apis";

const form:{
    AgentLoginForm:AgentLoginForm
} = {
    AgentLoginForm:AgentLoginForm
};

const model:{
    AgentLoginTokenModel:AgentLoginTokenModel
} = {
    AgentLoginTokenModel:AgentLoginTokenModel
};


export default Apis;

export {
    form,
    model,
    AgentLoginForm,
    AgentLoginTokenModel,
};