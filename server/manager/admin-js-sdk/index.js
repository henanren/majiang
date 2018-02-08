'use strict';

import LoginForm from './form/LoginForm';
import PageForm from './form/PageForm';
import PayForm from './form/PayForm';
import SettingForm from './form/SettingForm';
import ValueForm from './form/ValueForm';
import LoginTokenModel from './model/LoginTokenModel';
import PageModel from './model/PageModel';
import RoomModel from './model/RoomModel';
import SettingModel from './model/SettingModel';
import UserModel from './model/UserModel';
import Apis from "./Apis";

const form:{
    LoginForm:LoginForm,
    PageForm:PageForm,
    PayForm:PayForm,
    SettingForm:SettingForm,
    ValueForm:ValueForm
} = {
    LoginForm:LoginForm,
    PageForm:PageForm,
    PayForm:PayForm,
    SettingForm:SettingForm,
    ValueForm:ValueForm
};

const model:{
    LoginTokenModel:LoginTokenModel,
    PageModel:PageModel,
    RoomModel:RoomModel,
    SettingModel:SettingModel,
    UserModel:UserModel
} = {
    LoginTokenModel:LoginTokenModel,
    PageModel:PageModel,
    RoomModel:RoomModel,
    SettingModel:SettingModel,
    UserModel:UserModel
};


export default Apis;

export {
    form,
    model,
    LoginForm,
    PageForm,
    PayForm,
    SettingForm,
    ValueForm,
    LoginTokenModel,
    PageModel,
    RoomModel,
    SettingModel,
    UserModel,
};