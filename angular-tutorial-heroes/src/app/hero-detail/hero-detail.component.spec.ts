import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';
import { HeroService } from '../hero.service';
import { HEROES } from '../mock-heroes';

import { HeroDetailComponent } from './hero-detail.component';

describe('HeroDetailComponent', () => {
  let component: HeroDetailComponent;
  let fixture: ComponentFixture<HeroDetailComponent>;
  let heroServiceSpy: any;

  beforeEach(async () => {
    const heroService = jasmine.createSpyObj('HeroService', ['getHero']);
    heroServiceSpy = heroService.getHero.and.returnValue(of(HEROES[0]));
    await TestBed.configureTestingModule({
      imports: [RouterTestingModule, FormsModule],
      declarations: [ HeroDetailComponent ],
      providers: [
        {provide: HeroService, useValue: heroService}
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HeroDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
