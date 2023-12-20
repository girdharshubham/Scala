package book

// implicit conversion
given pretty[T <: Product]: Conversion[T, Unit] = (a: T) =>
  for element <- 0 until a.productArity do println(s"${a.productElementName(element)}: ${a.productElement(element)}")
final case class Config(name: String = "Default Server", port: Int = 8080)
given config: Config                            = Config()
def startServer(using config: Config)           = s"starting server with config: ${config}"

@main def main =
  pretty(config)
  println(startServer)
