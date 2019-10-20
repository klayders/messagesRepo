const merge = require('webpack-merge');
const common = require('./webpack.common.js');
const path = require('path');
const { CleanWebpackPlugin } = require('clean-webpack-plugin');

module.exports = merge(common, {
  mode: 'production',
  plugins: [
    new CleanWebpackPlugin(),
  ],
  output: {
    filename: 'main.js',
    path: path.resolve(__dirname, 'src', 'main', 'resources', 'static', 'js'),
  },
});
