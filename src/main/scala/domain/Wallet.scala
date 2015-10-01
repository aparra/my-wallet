package domain

case class Wallet(money: BigDecimal, cards: List[Card], documents: List[Document])
