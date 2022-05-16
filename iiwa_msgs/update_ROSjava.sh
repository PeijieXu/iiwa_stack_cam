# copy the new jar files to iiwa_ros_java

rm -rf rosjava_messages/

docker run --name iiwa_msgs -it iiwa_msgs:latest /bin/bash -c \
  "source devel/setup.bash && \
  genjava_message_artifacts --verbose -p iiwa_msgs actionlib_msgs geometry_msgs std_msgs"
docker cp iiwa_msgs:/catkin_ws/devel/share/maven/org/ros/rosjava_messages rosjava_messages
docker rm iiwa_msgs

cp rosjava_messages/actionlib_msgs/1.12.7/actionlib_msgs-1.12.7.jar ../iiwa_ros_java/ROSJavaLib
cp rosjava_messages/geometry_msgs/1.12.7/geometry_msgs-1.12.7.jar ../iiwa_ros_java/ROSJavaLib
cp rosjava_messages/iiwa_msgs/2.3.1/iiwa_msgs-2.3.1.jar ../iiwa_ros_java/ROSJavaLib
cp rosjava_messages/std_msgs/0.5.11/std_msgs-0.5.11.jar ../iiwa_ros_java/ROSJavaLib
