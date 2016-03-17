rm -rf latest/api/*
cp -R /src/shapes/SRDF/srdf/jvm/target/scala-2.11/api/* latest/api
git add -A
git commit -m "Updating site"
git push
