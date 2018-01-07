Spring Boot 配置 ERROR, WARN, INFO 三种日志级别。如果需要 Debug 级别的日志。在 src/main/resources/application.properties 中配置数据源信息。
debug=true

默认情况下， Spring Boot 日志只会输出到控制台，并不会写入到日志文件，因此，对于正式环境的应用，
	我们需要通过在 application.properites 文件中配置 logging.file 文件名称和 logging.path 文件路径，将日志输出到日志文件中。