#!/bin/sh

# 复制项目的文件到对应docker路径，便于一键生成镜像。
usage() {
	echo "Usage: sh copy.sh"
	exit 1
}


# copy sql
echo "begin copy sql "
cp ../sql/ry_20231130.sql ./mysql/db
cp ../sql/ry_config_20231204.sql ./mysql/db

# copy html
echo "begin copy html "
cp -r ../yuji-ui/dist/** ./nginx/html/dist


# copy jar
echo "begin copy yuji-gateway "
cp ../yuji-gateway/target/yuji-gateway.jar ./yuji/gateway/jar

echo "begin copy yuji-auth "
cp ../yuji-auth/target/yuji-auth.jar ./yuji/auth/jar

echo "begin copy yuji-visual "
cp ../yuji-visual/yuji-monitor/target/yuji-visual-monitor.jar  ./yuji/visual/monitor/jar

echo "begin copy yuji-modules-system "
cp ../yuji-modules/yuji-system/target/yuji-modules-system.jar ./yuji/modules/system/jar

echo "begin copy yuji-modules-file "
cp ../yuji-modules/yuji-file/target/yuji-modules-file.jar ./yuji/modules/file/jar

echo "begin copy yuji-modules-job "
cp ../yuji-modules/yuji-job/target/yuji-modules-job.jar ./yuji/modules/job/jar

echo "begin copy yuji-modules-gen "
cp ../yuji-modules/yuji-gen/target/yuji-modules-gen.jar ./yuji/modules/gen/jar

