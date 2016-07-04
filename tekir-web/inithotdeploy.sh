mvn clean compile war:exploded -PDevelopment -DskipTests=true

cd target/deployments/tekir.war/

rm -rf WEB-INF/lib/telve-core-4.0.0.Beta3.jar
#rm -rf WEB-INF/lib/telve-core-model-4.0.0.Beta3.jar
#rm -rf WEB-INF/lib/telve-idm-model-4.0.0.Beta3.jar
#rm -rf WEB-INF/lib/telve-idm-4.0.0.Beta3.jar
#rm -rf WEB-INF/lib/telve-bpm-4.0.0.Beta3.jar
#rm -rf WEB-INF/lib/telve-jcr-4.0.0.Beta3.jar
#rm -rf WEB-INF/lib/telve-dynaform-4.0.0.Beta3.jar
#rm -rf WEB-INF/lib/telve-gallery-4.0.0.Beta3.jar




ln -s ~/git/telve4/telve-core/target/classes/ WEB-INF/lib/telve-core-4.0.0.Beta3.jar
#ln -s ~/git/telve4/telve-core-model/target/classes/ WEB-INF/lib/telve-core-model-4.0.0.Beta3.jar
#ln -s ~/git/telve4/telve-idm-model/target/classes/ WEB-INF/lib/telve-idm-model-4.0.0.Beta3.jar
#ln -s ~/git/telve4/telve-idm/target/classes/ WEB-INF/lib/telve-idm-4.0.0.Beta3.jar
#ln -s ~/git/telve4/telve-bpm/target/classes/ WEB-INF/lib/telve-bpm-4.0.0.Beta3.jar
#ln -s ~/git/telve4/telve-jcr/target/classes/ WEB-INF/lib/telve-jcr-4.0.0.Beta3.jar
#ln -s ~/git/telve4/telve-dynaform/target/classes/ WEB-INF/lib/telve-dynaform-4.0.0.Beta3.jar
#ln -s ~/git/telve4/telve-gallery/target/classes/ WEB-INF/lib/telve-gallery-4.0.0.Beta3.jar



cd ..
touch tekir.war.dodeploy
cd ..
cd ..
