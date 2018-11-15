/**
 * Created by jojoldu@gmail.com on 2018. 11. 11.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

const webpack = require('webpack');

module.exports = {
    mode: 'development',
    output: {
        publicPath: '/',
    },
    devtool: 'inline-source-map',
    devServer: {
        https: true,
        hot: true,
        historyApiFallback: true,
        publicPath: '/',
        port: 3000,
        // proxy: {
        //     "**": "http://localhost:9200"
        // }
    },
    plugins: [
        new webpack.HotModuleReplacementPlugin()
    ]
};