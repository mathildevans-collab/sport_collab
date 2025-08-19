export interface Seance {
  id: number;
  titre: string;
  description?: string;
  duree: number;        // durée en minutes
  exercices: Exercice[];
}

export interface Exercice {
  nom: string;
  repetitions: number;
  series: number;
}
