import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';
import { HeroService } from '../hero.service';
import { HEROES } from '../mock-heroes';

import { HeroSearchComponent } from './hero-search.component';

describe('HeroSearchComponent', () => {
  let component: HeroSearchComponent;
  let fixture: ComponentFixture<HeroSearchComponent>;
  let heroServiceSpy: any;

  beforeEach(async () => {
    const heroService = jasmine.createSpyObj('HeroService', ['searchHero']);
    heroServiceSpy = heroService.searchHero.and.returnValue(of({heroes:[], term: '', complete: false}))
    await TestBed.configureTestingModule({
      imports: [RouterTestingModule, FormsModule],
      declarations: [ HeroSearchComponent ],
      providers: [{provide: HeroService, useValue: heroService}]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HeroSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
