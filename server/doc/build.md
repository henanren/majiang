
cd maven
./install.sh

mvn clean 

mkdir ./project/distribution/target/distribution-2.0.0-SNAPSHOT-bin

git config --global user.name "小草"
git config --global user.email "zuoge85@gmail.com"
git clone https://zuoge85:sbfgfg03423@code.aliyun.com/majiang/server-publish-debug.git ./target

mvn package -D maven.test.skip=true

git config --global user.name "小草"
git config --global user.email "zuoge85@gmail.com"
git clone https://zuoge85:sbfgfg03423@code.aliyun.com/majiang/server-publish-debug.git ./target
cd target
git commit -m "test"
git push -u origin master





https://username:password@gitlab.company.com/group/project.git

https://zuoge85:sbfgfg03423@code.aliyun.com/majiang/server-publish-debug.git




git clone git@code.aliyun.com:majiang/server-publish-debug.git
cd server-publish-debug


git commit -m "add $CODE_VERSION"
git push -u origin master