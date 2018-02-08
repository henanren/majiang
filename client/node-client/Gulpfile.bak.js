'use strict';

var del = require('del');
var fs = require('fs');
var gulp = require('gulp');
var plugins = require('gulp-load-plugins')();
var concat = require('gulp-concat');
var uglify = require('gulp-uglify');
var sourcemaps = require('gulp-sourcemaps');
var sass = require('gulp-sass');
var autoprefixer = require('gulp-autoprefixer');
var plumber = require('gulp-plumber');
var rename = require('gulp-rename');
var minifyCss = require('gulp-minify-css');
var copy = require('gulp-copy2');
var includeTag = require("gulp-include-tag");
var connect = require('gulp-connect');
var header = require('gulp-header');
var footer = require('gulp-footer');
var inlinesource = require('gulp-inline-source');
var htmlAutoprefixer = require("gulp-html-autoprefixer");
var minifyHTML = require('gulp-minify-html');
var minifyInline = require('gulp-minify-inline');
var babel = require('gulp-babel');
var browserify = require('browserify');
var transform = require('vinyl-transform');
var gutil = require('gulp-util');
var source = require('vinyl-source-stream');
var buffer = require('vinyl-buffer');
var babelify = require("babelify");
var replace = require('gulp-replace');

//var targetPath = "target";

var DIST_PAGE = "dist";

function distPath(path) {
    if (path == null) {
        return DIST_PAGE;
    }
    return DIST_PAGE + path;
}

var VERSION = 10;


var IS_DEBUG = !gulp.env.release;
var IS_PUBLISH = !gulp.env.publish;

console.log("是否调试模式:", IS_DEBUG);
console.log("是否发布:", IS_PUBLISH);

var PUBLISH_PAGE = "../tatata-wap/src/main/webapp";
var PUBLISH_PAGE_TARGET = "../tatata-wap/target/wap-0.0.1-SNAPSHOT";

function publishPath(path) {
    if (path == null) {
        return PUBLISH_PAGE;
    }
    return PUBLISH_PAGE + path;
}

function publishPathTarget(path) {
    if (path == null) {
        return PUBLISH_PAGE_TARGET;
    }
    return PUBLISH_PAGE_TARGET + path;
}

var paths = {
    js: ['js/main.js', 'js/frame.js', 'js/core.js'],
    scripts: ['wap/*.jsx', 'wap/*/*.jsx'],
    images: 'images/**',
    apis: ['api/*.js', 'api/*/*.js'],
    commons: ['common/*.jsx', 'common/*/*.jsx'],
    patch: ['patch/patch.js'],
    apisIndex: ['api/index'],
    pages: ['page/*.html', 'page/*/*.html', '!page/common/*.html'],
    ajaxPages: ['page/*.html', 'page/*/*.html', '!page/frame.html', '!page/common/*.html'],
    scss: ['scss/*.scss'],
    lib: ['js/lib.js']
};

var jsLib = [
    'lib/jquery/dist/jquery.min.js',
    'lib/lazysizes/lazysizes.min.js',
    'lib/fastclick-1.0.6/lib/fastclick.js',
    'lib/HTML5-History-API/history.min.js',
    'lib/dropload-0.7.0/dist/dropload.min.js',
    'lib/lrz/dist/lrz.all.bundle.js',
    'lib/onepx.js'
];

if (IS_DEBUG) {
    jsLib.push(
        'lib/react-15.0.1/build/react.js',
        'lib/react-15.0.1/build/react-dom.js',
        'lib/require.min.js'
    );
} else {
    jsLib.push(
        'lib/react-15.0.1/build/react.min.js',
        'lib/react-15.0.1/build/react-dom.min.js',
        'lib/require.min.js'
    );
}

// Not all tasks need to use streams
// A gulpfile is just another node program and you can use any package available on npm
gulp.task('clean', function () {
    // You can use multiple globbing patterns as you would with `gulp.src`
    return del(['dist/*'], {force: true});
});


gulp.task('scripts', ['scriptsAmd']);

gulp.task('scriptsAmd', function(){
    return scriptsTask(true);
});

function getBabelifyConfig() {
    return {
        "presets": [
            "es2015",
            "react"
        ],
        "compact": false,
        "babelrc": false,
        "plugins": [
            "syntax-flow",
            "transform-es2015-for-of",
            "transform-es2015-typeof-symbol",
            "transform-flow-strip-types",
            "transform-strict-mode",
            "transform-class-properties"
        ]
    };
}


function scriptsTask(isAmd) {
    var bableCfg = {
        "presets": [
            "es2015",
            "react"
        ],
        "compact": false,
        "babelrc": false,
        "plugins": [
            "syntax-flow",
            "transform-es2015-for-of",
            "transform-es2015-typeof-symbol",
            "transform-flow-strip-types",
            "transform-strict-mode",
            "transform-class-properties"
        ]
    };
    if (isAmd) {
        bableCfg.plugins.push("transform-es2015-modules-amd");
    }
    var pipe = gulp.src(paths.scripts)
        .pipe(plumber())
        .pipe(babel(bableCfg))
        .pipe(sourcemaps.init());

    if (!IS_DEBUG) {
        pipe = pipe.pipe(uglify());
    }

    if (!isAmd) {
        pipe = pipe.pipe(rename(function (path) {
            path.basename += ".include";
        }))
    }
    //
    pipe = pipe.pipe(sourcemaps.write('.'))
        .pipe(gulp.dest(distPath('/wap')));

    if (IS_PUBLISH) {
        return pipe
            .pipe(gulp.dest(publishPath('/wap')))
            .pipe(gulp.dest(publishPathTarget('/wap')))
            .pipe(connect.reload());
    } else {
        return pipe.pipe(connect.reload());
    }
}

gulp.task('apis', function () {

    var b = browserify('./api/ExportBrowserApis.js').transform(babelify, getBabelifyConfig());

    var pipe = b.bundle()
        .pipe(source('apis.min.js'))
        .pipe(buffer())
        .pipe(plumber())
        .pipe(sourcemaps.init());

    if (!IS_DEBUG) {
        pipe = pipe.pipe(uglify({compress: true}));
    }

    pipe = pipe.on('error', gutil.log)
        .pipe(sourcemaps.write('.'))
        .pipe(gulp.dest('dist/wap'));

    if (IS_PUBLISH) {
        return pipe
            .pipe(gulp.dest(publishPath('/wap')))
            .pipe(gulp.dest(publishPathTarget('/wap')))
            .pipe(connect.reload());
    } else {
        return pipe.pipe(connect.reload());
    }

});

gulp.task('commons', function () {
    var b = browserify('./common/ExportCommons.jsx').transform(babelify, getBabelifyConfig());

    var pipe = b.bundle()
        .pipe(source('commons.min.js'))
        .pipe(buffer())
        .pipe(plumber())
        .pipe(sourcemaps.init());

    if (!IS_DEBUG) {
        pipe = pipe.pipe(uglify({compress: true}))
    }

    pipe = pipe.on('error', gutil.log)
        .pipe(sourcemaps.write('.'))
        .pipe(gulp.dest('dist/wap'));

    if (IS_PUBLISH) {
        return pipe.pipe(gulp.dest(publishPath('/wap')))
            .pipe(gulp.dest(publishPathTarget('/wap'))
                .pipe(connect.reload()));
    } else {
        return pipe.pipe(connect.reload());
    }
});

gulp.task('patch', function () {
    var pipe = gulp.src(paths.patch)
        .pipe(sourcemaps.init())
        .pipe(replace('${version}', VERSION.toString()))
        .pipe(babel(getBabelifyConfig()))
        .pipe(rename(function (path) {
            path.basename += ".min";
        }));


    if (!IS_DEBUG) {
        pipe = pipe.pipe(uglify({compress: true}))
    }

    pipe = pipe.on('error', gutil.log)
        .pipe(sourcemaps.write('.'))
        .pipe(gulp.dest('dist/wap'));

    if (IS_PUBLISH) {
        return pipe
            .pipe(gulp.dest(publishPath('/wap')))
            .pipe(gulp.dest(publishPathTarget('/wap')))
            .pipe(connect.reload());
    } else {
        return pipe.pipe(connect.reload());
    }
});

gulp.task('libConcat', function () {
    var pipe = gulp.src(jsLib)
        .pipe(plumber())
        .pipe(sourcemaps.init())
        .pipe(concat('lib.min.js'));

    if (!IS_DEBUG) {
        pipe = pipe.pipe(uglify())
    }

    pipe = pipe.pipe(uglify()).pipe(sourcemaps.write('.')).pipe(gulp.dest(distPath('/lib')));

    if (IS_PUBLISH) {
        return pipe
            .pipe(gulp.dest(publishPath('/lib')))
            .pipe(gulp.dest(publishPathTarget('/lib')))
    } else {
        return pipe;
    }
});


gulp.task('scss', function () {
    var pipe = gulp.src(paths.scss)
        .pipe(plumber())
        // .pipe(sourcemaps.init())
        .pipe(sass().on('error', sass.logError))
        .pipe(autoprefixer(autoprefixerOption))
        //压缩后的文件处理
        .pipe(rename(function (path) {
            path.basename += ".min";
        }));


    if (!IS_DEBUG) {
        pipe = pipe.pipe(minifyCss())
    }

    // pipe.pipe(sourcemaps.write('.'))
    pipe.pipe(gulp.dest(distPath('/ncss')));

    if (IS_PUBLISH) {
        return pipe
            .pipe(gulp.dest(publishPath('/ncss')))
            .pipe(gulp.dest(publishPathTarget('/ncss')))
            .pipe(connect.reload());
    } else {
        return pipe.pipe(connect.reload());
    }
});

var libPath = [
    {src: 'lib/jquery/dist/*', dest: '/lib/jquery/*'},
    {src: 'lib/HTML5-History-API/*', dest: '/lib/HTML5-History-API/'},
    {src: 'lib/require.js', dest: '/lib/'},
    {src: 'lib/lazysizes/*', dest: '/lib/lazysizes/'},
    {src: 'lib/dropload-0.7.0/dist/*', dest: '/lib/dropload/'},
    {src: 'lib/flexslider/**', dest: '/lib/flexslider/'},
    {src: 'lib/font-awesome-4.5.0/**', dest: '/lib/font-awesome/'},
    {src: 'lib/lrz/dist/**', dest: '/lib/lrz/'},
    {src: 'lib/onepx.js', dest: '/lib/onepx.js'}
];

gulp.task('copylib', function () {
    var copyPaths = [];
    libPath.forEach(function(item) {
        copyPaths.push({
            src: item.src,
            dest: distPath(item.dest)
        });
        if (IS_PUBLISH) {
            copyPaths.push({
                src: item.src,
                dest: publishPath(item.dest)
            });
            copyPaths.push({
                src: item.src,
                dest: publishPathTarget(item.dest)
            });
        }
    });
    return copy(copyPaths);
});

var autoprefixerOption = {browsers: ['> 1%', 'IE 7'], cascade: false};
var minifyInlineOptions = {
    js: {
        output: {
            comments: true
        }
    }
};
var htmlMinifyOption = {empty: true, comments: true};

gulp.task("html", function () {
    var result = gulp.src(paths.pages)
        .pipe(plumber());


    result = result.pipe(replace('${version}', VERSION.toString())).pipe(includeTag());
    result = result.pipe(htmlAutoprefixer(autoprefixerOption));

    if (!IS_DEBUG) {
        result = result.pipe(minifyHTML(htmlMinifyOption))
            .pipe(minifyInline(minifyInlineOptions))
            .pipe(inlinesource({
                rootpath: distPath('/'),
                compress: false
            }))
    }
    result = result.pipe(gulp.dest(distPath('')));

    if (IS_PUBLISH) {
        return result
            .pipe(gulp.dest(publishPath('')))
            .pipe(gulp.dest(publishPathTarget('')))
            .pipe(connect.reload());
    } else {
        return result.pipe(connect.reload())
    }
});

gulp.task("fullHtml", function () {
    var fullStart, fullEnd;

    var frame = fs.readFileSync('page/frame.html', 'utf8')
        .replace(/\$\{version\}/g, VERSION.toString());


    var arr = frame.split("${content}");
    fullStart = arr[0];
    fullEnd = arr[1];

    var result = gulp.src(paths.ajaxPages)
        .pipe(plumber());

    result = result.pipe(includeTag());
    result = result.pipe(header(fullStart))
        .pipe(footer(fullEnd))
        .pipe(rename(function (path) {
            path.basename += ".full";
        }))
        .pipe(htmlAutoprefixer(autoprefixerOption));

    if (!IS_DEBUG) {
        result = result
            .pipe(minifyHTML(htmlMinifyOption))
            .pipe(minifyInline(minifyInlineOptions))
            .pipe(inlinesource({
                rootpath: distPath('/'),
                compress: false
            }));
    }

    result = result.pipe(gulp.dest(distPath(''))).pipe(connect.reload());

    if (IS_PUBLISH) {
        return result
            .pipe(gulp.dest(publishPath('')))
            .pipe(gulp.dest(publishPathTarget('')))
            .pipe(connect.reload());
    } else {
        return result.pipe(connect.reload())
    }
});


gulp.task('js', function () {
    var result = gulp.src(paths.js)
        .pipe(plumber())
        .pipe(sourcemaps.init())
        .pipe(concat('all.min.js'));

    if (!IS_DEBUG) {
        result = result.pipe(uglify())
    }

    result = result.pipe(sourcemaps.write('./maps'))
        .pipe(gulp.dest(distPath('/njs')));

    if (IS_PUBLISH) {
        return result
            .pipe(gulp.dest(publishPath('/njs')))
            .pipe(gulp.dest(publishPathTarget('/njs')))
            .pipe(connect.reload());
    } else {
        return result.pipe(connect.reload());
    }
});

gulp.task('images', function () {
    var copyPaths = [
        {src: paths.images, dest: distPath('/images/')}
    ];
    if (IS_PUBLISH) {
        copyPaths.push(
            {src: paths.images, dest: publishPath('/images/')},
            {src: paths.images, dest: publishPathTarget('/images/')}
        )
    }
    return copy(copyPaths);
});


gulp.task('connect', function () {
    connect.server({
        port: 8081,
        root: 'dist',
        livereload: true
    });
});


gulp.task('watch', function () {
    gulp.watch(paths.scripts, ['scripts']);
    gulp.watch(paths.images, ['images']);
    gulp.watch(paths.scss, ['scss']);
    gulp.watch(paths.apis, ['apis']);
    gulp.watch(paths.patch, ['patch']);
    gulp.watch(paths.commons, ['commons']);
    gulp.watch(paths.pages, ['html']);
    gulp.watch(paths.pages, ['fullHtml']);
    gulp.watch(paths.js, ['js']);
});

gulp.task('dist', ['scripts', 'apis', 'libConcat',
    'patch', 'commons', 'scss', 'images', 'html', 'fullHtml', 'copylib', 'js']);

gulp.task('default', ['watch', 'connect']);
