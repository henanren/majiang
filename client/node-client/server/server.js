'use strict';

var http = require('http');
var express = require('express');
var url = require('url');
var fs = require("fs");
var path = require("path");

var app = express();
var basePath = "../dist/web";


app.use('*', function (req, res) {
    var url = req.baseUrl;
    console.log('baseUrl: %s', req.baseUrl);
    var index = url.indexOf("?");
    if (index > -1) {
        url = url.substring(0, index);
    }

    if (url.indexOf(".png") > -1) {
        res.setHeader('Content-Type', 'image/png');
    } else if (url.indexOf(".jpg") > -1) {
        res.setHeader('Content-Type', 'image/jpg');
    } else if (url.indexOf(".json") > -1) {
        res.setHeader('Content-Type', 'application/json');
    } else {
        res.setHeader('Content-Type', 'text/html');
    }

    fs.readFile(getFilePath(url), (err, data) => {
        if (err) {
            res.statusCode = 503;
            res.end("<h1>503:" + err + "</h1>");
            console.error(err);
        } else {
            res.end(data);
        }
    });
});

var server = app.listen(3000, function () {
    var host = server.address().address;
    var port = server.address().port;
    console.log('Example app listening at http://%s:%s', host, port);
});

function getFilePath(url) {
    var index = url.indexOf('?');
    if (index > -1) {
        url = url.substr(0, index);
    }
    return basePath + path.normalize(url);
}

