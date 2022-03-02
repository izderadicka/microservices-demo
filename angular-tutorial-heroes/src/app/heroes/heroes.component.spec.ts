import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';
import { HeroService } from '../hero.service';
import { HEROES } from '../mock-heroes';

import { HeroesComponent } from './heroes.component';

describe('HeroesComponent', () => {
  let component: HeroesComponent;
  let fixture: ComponentFixture<HeroesComponent>;
  let heroServiceSpy: any;

  beforeEach(async () => {
    const heroService = jasmine.createSpyObj('HeroService', ['getHeroes']);
    heroServiceSpy = heroService.getHeroes.and.returnValue(of(HEROES));
    await TestBed.configureTestingModule({
      imports: [RouterTestingModule, FormsModule],
      declarations: [ HeroesComponent ],
      providers: [{provide: HeroService, useValue: heroService}]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HeroesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
