import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';


@Component({
  selector: 'app-root',
  templateUrl: './home.html',  
  styleUrls: ['./home.css']   
})
export class HomeComponent {
  // État du menu de navigation (ouvert/fermé)
  navOpen = false;

  

  // Données pour les cartes affichées dans la page
  cards = [
    { id: 1, title: 'Cardio Training', description: 'Boost your endurance.' },
    { id: 2, title: 'Weightlifting', description: 'Build your strength.' },
    { id: 3, title: 'Yoga', description: 'Find your balance.' },
  ];

  // État d'affichage des cartes (visible ou caché) pour les animations
  cardsState: { [key: number]: 'hidden' | 'visible' } = {};

  constructor(private fb: FormBuilder) {
    // Initialise toutes les cartes comme cachées
    this.cards.forEach(card => this.cardsState[card.id] = 'hidden');
  }

  // Méthode pour basculer l'état du menu de navigation
  toggleNav() {
    this.navOpen = !this.navOpen;
  }

}
