import { Component } from '@angular/core';
import { CommonModule } from '@angular/common'; 
import { FormsModule } from '@angular/forms';
import { SeanceService } from '../../services/seance';
import { Router } from '@angular/router';

@Component({
  standalone: true,
  selector: 'app-creation-seance',
  templateUrl: './creation-seance.html',
  styleUrls: ['./creation-seance.css'],
  imports: [CommonModule, FormsModule] 
})
export class CreationSeanceComponent {
  // Champs du formulaire
  titre: string = '';
  description: string = '';
  duree: number = 30;

  // Liste d'exercices à ajouter à la séance
  exercices: { nom: string; repetitions: number; series: number }[] = [];

  // Exercice en cours de saisie
  nouvelExercice = {
    nom: '',
    repetitions: 0,
    series: 0
  };

  constructor(
    private  readonly router: Router,
    private  readonly seanceService: SeanceService // Injection du service
  ) {}

  // Ajouter un exercice à la liste
  ajouterExercice() {
    if (this.nouvelExercice.nom.trim() !== '') {
      this.exercices.push({ ...this.nouvelExercice });
      this.nouvelExercice = { nom: '', repetitions: 0, series: 0 };
    }
  }

  // Créer une séance à partir des données
 creerSeance() {
  const seance = {
    titre: this.titre,
    description: this.description,
    duree: this.duree,
    exercices: this.exercices
  };

  this.seanceService.ajouterSeance(seance);
  this.router.navigate(['/liste-seances']);
}

  
}
