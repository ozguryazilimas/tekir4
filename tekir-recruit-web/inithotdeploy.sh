mvn clean compile war:exploded -PDevelopment -DskipTests=true

cd target/deployments/tekir-recruit.war/

#rm -rf WEB-INF/lib/telve-core-4.0.0.Beta6-SNAPSHOT.jar
#rm -rf WEB-INF/lib/telve-core-model-4.0.0.Beta6-SNAPSHOT.jar
#rm -rf WEB-INF/lib/telve-idm-model-4.0.0.Beta6-SNAPSHOT.jar
#rm -rf WEB-INF/lib/telve-idm-4.0.0.Beta6-SNAPSHOT.jar
#rm -rf WEB-INF/lib/telve-layout-4.0.0.Beta6-SNAPSHOT.jar
#rm -rf WEB-INF/lib/telve-keycloak-4.0.0.Beta6-SNAPSHOT.jar
#rm -rf WEB-INF/lib/telve-bpm-4.0.0.Beta6-SNAPSHOT.jar
#rm -rf WEB-INF/lib/telve-jcr-4.0.0.Beta6-SNAPSHOT.jar
#rm -rf WEB-INF/lib/telve-dynaform-4.0.0.Beta6-SNAPSHOT.jar
#rm -rf WEB-INF/lib/telve-gallery-4.0.0.Beta6-SNAPSHOT.jar
#rm -rf WEB-INF/lib/telve-note-4.0.0.Beta6-SNAPSHOT.jar
#rm -rf WEB-INF/lib/telve-attachment-4.0.0.Beta6-SNAPSHOT.jar
#rm -rf WEB-INF/lib/telve-attachment-ui-4.0.0.Beta6-SNAPSHOT.jar
#rm -rf WEB-INF/lib/telve-attachment-modeshape-4.0.0.Beta6-SNAPSHOT.jar
#rm -rf WEB-INF/lib/tekir-core-4.0.0-SNAPSHOT.jar
#rm -rf WEB-INF/lib/tekir-contact-4.0.0-SNAPSHOT.jar
#rm -rf WEB-INF/lib/tekir-activity-4.0.0-SNAPSHOT.jar
#rm -rf WEB-INF/lib/tekir-voucher-4.0.0-SNAPSHOT.jar
#rm -rf WEB-INF/lib/tekir-account-notes-4.0.0-SNAPSHOT.jar
#rm -rf WEB-INF/lib/tekir-feed-4.0.0-SNAPSHOT.jar
rm -rf WEB-INF/lib/tekir-recruit-4.0.0-SNAPSHOT.jar



#ln -s ~/git/telve4/telve-core/target/classes/ WEB-INF/lib/telve-core-4.0.0.Beta6-SNAPSHOT.jar
#ln -s ~/git/telve4/telve-core-model/target/classes/ WEB-INF/lib/telve-core-model-4.0.0.Beta6-SNAPSHOT.jar
#ln -s ~/git/telve4/telve-idm-model/target/classes/ WEB-INF/lib/telve-idm-model-4.0.0.Beta6-SNAPSHOT.jar
#ln -s ~/git/telve4/telve-idm/target/classes/ WEB-INF/lib/telve-idm-4.0.0.Beta6-SNAPSHOT.jar
#ln -s ~/git/telve4/telve-layout/target/classes/ WEB-INF/lib/telve-layout-4.0.0.Beta6-SNAPSHOT.jar
#ln -s ~/git/telve4/telve-keycloak/target/classes/ WEB-INF/lib/telve-keycloak-4.0.0.Beta6-SNAPSHOT.jar
#ln -s ~/git/telve4/telve-bpm/target/classes/ WEB-INF/lib/telve-bpm-4.0.0.Beta6-SNAPSHOT.jar
#ln -s ~/git/telve4/telve-jcr/target/classes/ WEB-INF/lib/telve-jcr-4.0.0.Beta6-SNAPSHOT.jar
#ln -s ~/git/telve4/telve-dynaform/target/classes/ WEB-INF/lib/telve-dynaform-4.0.0.Beta6-SNAPSHOT.jar
#ln -s ~/git/telve4/telve-gallery/target/classes/ WEB-INF/lib/telve-gallery-4.0.0.Beta6-SNAPSHOT.jar
#ln -s ~/git/telve4/telve-note/target/classes/ WEB-INF/lib/telve-note-4.0.0.Beta6-SNAPSHOT.jar
#ln -s ~/git/telve4/telve-attachment/target/classes/ WEB-INF/lib/telve-attachment-4.0.0.Beta6-SNAPSHOT.jar
#ln -s ~/git/telve4/telve-attachment-ui/target/classes/ WEB-INF/lib/telve-attachment-ui-4.0.0.Beta6-SNAPSHOT.jar
#ln -s ~/git/telve4/telve-attachment-modeshape/target/classes/ WEB-INF/lib/telve-attachment-modeshape-4.0.0.Beta6-SNAPSHOT.jar
#ln -s ~/git/tekir4/tekir-core/target/classes/ WEB-INF/lib/tekir-core-4.0.0-SNAPSHOT.jar
#ln -s ~/git/tekir4/tekir-contact/target/classes/ WEB-INF/lib/tekir-contact-4.0.0-SNAPSHOT.jar
#ln -s ~/git/tekir4/tekir-activity/target/classes/ WEB-INF/lib/tekir-activity-4.0.0-SNAPSHOT.jar
#ln -s ~/git/tekir4/tekir-voucher/target/classes/ WEB-INF/lib/tekir-voucher-4.0.0-SNAPSHOT.jar
#ln -s ~/git/tekir4/tekir-feed/target/classes/ WEB-INF/lib/tekir-feed-4.0.0-SNAPSHOT.jar
ln -s ~/git/tekir4/tekir-recruit/target/classes/ WEB-INF/lib/tekir-recruit-4.0.0-SNAPSHOT.jar


cd ..
touch tekir-recruit.war.dodeploy
cd ..
cd ..
