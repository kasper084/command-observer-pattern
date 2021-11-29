package observer

trait Observer[T] {

  def handleUpdate(subject: T)
}
