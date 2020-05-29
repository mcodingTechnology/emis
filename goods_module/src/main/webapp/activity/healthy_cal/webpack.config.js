var path = require('path');
var webpack = require('webpack');
new webpack.DefinePlugin({
    "process.env": {
        NODE_ENV: JSON.stringify("production")
    }
});
module.exports = {
    devtool: 'eval',
    entry: {
        main:['webpack-dev-server/client?http://localhost:3200',
        'webpack/hot/only-dev-server','./render/index'
       ],
        vendor: ['react','react-dom','jquery','react-tappable'],
        index: [ './render/index.js'],
        weightCalculate:['./render/weightCalculate.js'],
        baseMetabolize:['./render/baseMetabolize.js'],
        bodyQuality:['./render/bodyQuality.js'],
        heartRate:['./render/heartRate.js'],
        manFit:['./render/manFit.js'],
        womanFit:['./render/womanFit.js'],
        protein:["./render/protein.js"]
    },
    plugins: [
        new webpack.HotModuleReplacementPlugin(),
        new webpack.optimize.MinChunkSizePlugin({
            compress: {
                warnings: false
            }
        }),
        new webpack.optimize.CommonsChunkPlugin({
            name:'vendor',
            minChunks: Infinity
        }) ,
        new webpack.optimize.UglifyJsPlugin({
		    compress: {
		        warnings: false
		    }
		})
    ],
    module: {
        loaders: [{
            test: /\.js$/,
            loaders: ['react-hot', 'babel'],
            include: [path.join(__dirname, 'render'), path.join(__dirname, 'component')]
        },
            { test: /\.css$/, loader: 'style-loader!css-loader' },
            { test: /\.scss$/, loader: 'style!css!sass?sourceMap'},
            { test: /\.(png|jpg)$/, loader: 'url-loader?limit=8192'}
        ]
    },
     
    output: {
        path: path.join(__dirname, 'dist'),
        filename: '[name].bundle.js',
        publicPath: './dist/'
    }
};
