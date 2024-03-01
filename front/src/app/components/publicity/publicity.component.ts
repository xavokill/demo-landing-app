import { CommonModule } from '@angular/common';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { NgbCarouselModule } from '@ng-bootstrap/ng-bootstrap';
import { PublicityItem } from '../../models/publicity/publicity-item.model';
import { PublicityService } from '../../services/publicity.service';
import { Subscription } from 'rxjs';
import { PublicityData } from '../../models/publicity/publicity-data.model';

@Component({
  selector: 'app-publicity',
  standalone: true,
  imports: [CommonModule, NgbCarouselModule],
  templateUrl: './publicity.component.html',
  styleUrl: './publicity.component.css',
})
export class PublicityComponent implements OnInit, OnDestroy {
  // Public Attributes
  carrousel!: PublicityItem[];

  // Private Attributes
  private publicitySubscription: Subscription | null;

  // Constructor
  constructor(private publicityService: PublicityService) {
    this.publicitySubscription = null;
  }

  // Life cycle hooks
  ngOnInit(): void {
    this.publicitySubscription = this.publicityService
      .getPublicity()
      .subscribe((publicityData: PublicityData) => {
        this.carrousel = publicityData.Data;
      });
  }

  ngOnDestroy(): void {
    if (this.publicitySubscription) {
      this.publicitySubscription.unsubscribe();
      this.publicitySubscription = null;
    }
  }
}
