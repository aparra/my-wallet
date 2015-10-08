package domain

case class Wallet(id: Long, money: BigDecimal, cards: List[Card], documents: List[Document])
