import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  standalone: true,
  selector: 'app-barre-navigation',
  templateUrl: './barre-navigation.html',
  styleUrls: ['./barre-navigation.css'],
  imports: [CommonModule, RouterModule]
})
export class BarreNavigationComponent {}

