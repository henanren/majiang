var dest = './build/dist',
    publish = '../project/mj-manager/src/main/resources/static',
    src = './src',
    mui = './node_modules/material-ui/src';

const modRewrite  = require('connect-modrewrite');

module.exports = {
    browserSync: {
        server: {
            // We're serving the src folder as well
            // for sass sourcemap linking
            baseDir: [dest, src],
            middleware: [
                modRewrite([
                    '^/(.*)\\.html$ /index.html [L]',
                    '!/ /index.html [L]'
                ])
            ]
        },
        // serveStatic: ['*.'],
        files: [
            dest + '/**'
        ]
    },
    markup: {
        src: src + "/www/**",
        dest: dest,
        publish: publish,
    },
    browserify: {
        // Enable source maps
        debug: true,
        // A separate bundle will be generated for each
        // bundle config in the list below
        bundleConfigs: [{
            entries: src + '/app/app.js',
            dest: dest,
            publish: publish,
            outputName: 'app.js'
        }],
        extensions: ['.js'],
    }
};
