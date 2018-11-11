const webpack = require('webpack');
const path = require('path');
const ExtractTextPlugin = require('extract-text-webpack-plugin');
const webpackMerge = require('webpack-merge');

const config = {
    entry: {
        // babel 7
        entry: ['@babel/polyfill', './src/js/entry.js']
    },
    module: {
        rules: [
            {
                test: /\.vue$/,
                loader: 'vue-loader',
                options: {
                    extractCSS: true
                }
            },
            {
                test: /\.js$/,
                exclude: /node_modules/,
                use: [{
                    loader: 'babel-loader',
                    options: {
                        presets: [
                            ['@babel/preset-env']
                        ]
                    }
                }]
            },
            {
                test: /\.css$/,
                loader: ExtractTextPlugin.extract({
                    fallback: "style-loader",
                    use: "css-loader",
                })
            },
            {
                test: /\.(jpe?g|png|gif|svg)$/,
                loader: 'url-loader',
                options: {
                    publicPath: '/static/',
                    name: 'images/[name].[ext]',
                    limit: 1
                }
            },
            {
                test: /\.ico$/,
                loader: 'file-loader',
                options: {
                    publicPath: '/static/',
                    name: 'icons/[name].[ext]',
                    limit: 1
                }
            },
            {
                test: /\.(eot|svg|ttf|woff|woff2)$/,
                loader: 'url-loader',
                options: {
                    publicPath: '/static/',
                    name: 'fonts/[name].[ext]',
                    limit: 1
                }
            }
        ]
    },
    plugins: [
        new webpack.ProvidePlugin({
            _: "lodash"
        }),
        new ExtractTextPlugin('css/[name].css')
    ]
};

module.exports = function (env) {
    return webpackMerge(config, require(`./webpack.${env}.js`));
};