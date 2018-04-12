package com.card.controller;


import com.card.domain.card.Card;
import com.card.service.card.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cards")
public class CardController {

	@Autowired
	private CardService cardService;

	@GetMapping("")
	public List<Card> listCard() {
		return cardService.findCard();
	}

	@GetMapping("/{cardId}")
	public Card getCard(@PathVariable Long cardId) {
		return cardService.findCardById(cardId);
	}

	@PostMapping("")
	public int saveCard(Card card) {
		return cardService.insert(card);
	}

	@PutMapping("/{cardId}")
	public int updateCard(Card card) {
		return cardService.update(card);
	}

	@DeleteMapping("/{cardId}")
	public int removeCard(@PathVariable Long cardId) {
		return cardService.deleteById(cardId);
	}


}
