#代码依赖git yum install git
#后端 依赖maven
#1、	wget http://repos.fedorapeople.org/repos/dchen/apache-maven/epel-apache-maven.repo -O /etc/yum.repos.d/epel-apache-maven.repo
#2、yum -y install apache-maven
#3、检查是否成功 mvn -version
#前端 依赖npm
#1、yum install -y nodejs
#2、node -v npm -v
#设置淘宝镜像--感觉还是原地址快https://registry.npmjs.org/
#3、npm config set registry https://registry.npm.taobao.org
#4、npm install -g @vue/cli@3.9.3
#5、npm install -g webpack@4.35.2
#6、npm install -g webpack-cli@3.3.6
#==================部署服务器===================
#1、删除源码
rm -rf /usr/soft/kiwi_src/kiwi_v2
#2、下载源码
git clone https://github.com/allen1104/kiwi_v2.git
#3、编译后端代码
cd /usr/soft/kiwi_src/kiwi_v2/kiwibackend/
mvn clean package
#4、编译前端代码
cd /usr/soft/kiwi_src/kiwi_v2/kiwi_web
npm install
npm run build
mv dist kiwi_web
#5、停后端服务器,并删除
pids=$(ps -ef | grep backend-0.0.1-SNAPSHOT| awk '{print $2}')
for pid in $pids
do
 echo  $pid
 kill -9  $pid
done
rm -rf /usr/soft/kiwi_backend/backend-0.0.1-SNAPSHOT.jar
rm -rf /usr/soft/kiwi_backend/nohup.out
#6、复制后端包
cp -rf /usr/soft/kiwi_src/kiwi_v2/kiwibackend/target/backend-0.0.1-SNAPSHOT.jar /usr/soft/kiwi_backend/
#7、启动后端服务器
nohup java -jar /usr/soft/kiwi_backend/backend-0.0.1-SNAPSHOT.jar &
#8、删除前端代码
rm -rf /usr/local/nginx/html/kiwi_web
#9、复制前端代码
cp -rf /usr/soft/kiwi_src/kiwi_v2/kiwi_web /usr/soft/kiwi_backend/