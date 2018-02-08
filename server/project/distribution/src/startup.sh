#!/bin/sh


nohup java   -server  -verbose:gc   -cp ./client majiang.client.ClientMain > log/ClientMain.log 2>&1 &
nohup java   -server  -verbose:gc   -cp ./manager majiang.client.ManagerMain > log/ManagerMain.log 2>&1 &
nohup java   -server  -verbose:gc   -cp ./boss game.boss.BossMain > log/BossMain.log 2>&1 &
nohup java   -server  -verbose:gc   -cp ./scene game.scene.SceneMain > log/SceneMain.log 2>&1 &
java   -server  -verbose:gc   -cp ./gateway game.gateway.GatewayMain > log/GatewayMain.log >&1 2>&1

