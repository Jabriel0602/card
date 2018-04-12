package com.card.controller;


import org.springframework.web.bind.annotation.*;

import javax.smartcardio.Card;
import java.util.List;


@RestController
@RequestMapping("/cards")
public class CardController {

	@GetMapping("")
	List<Card> listCard() {

		return null;
	}

	@GetMapping("/{id}")
	Card getCard() {

		return null;
	}

	@PostMapping("")
	Card saveCard() {

		return null;
	}

	@PutMapping("/{id}")
//	@PatchMapping("{id}")
	Card updateCard() {

		return null;
	}

	@DeleteMapping("/{id}")
	Card removeCard() {

		return null;
	}


}
