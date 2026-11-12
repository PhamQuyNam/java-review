lệnh   | Các goal đã chạy theo thứ tự| File hoặc thư mục sinh ra.
| --- | --- | --- |
mvn clean| clean | Xóa folder target
mvn compile| resource -> compile |tạo ra các file có trong folder target\classes
mvn test | resources -> compile -> testResources -> testCompile -> test| tạo ra các file trong folder target\test-classes
mvn package|resources -> compile -> testResources -> testCompile -> test -> jar| tạo ra file java-review-1.0-SNAPSHOT.jar