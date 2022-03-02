import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HEROES } from '../mock-heroes';

import { DashboardComponent } from './dashboard.component';
import { HeroSearchComponent } from '../hero-search/hero-search.component';
import { HeroService } from '../hero.service';
import { RouterModule } from '@angular/router';

describe('DashboardComponent', () => {
  let component: DashboardComponent;
  let fixture: ComponentFixture<DashboardComponent>;
  let heroServiceSpy: any;

  beforeEach(async () => {
    const heroService = jasmine.createSpyObj('HeroService', ['getRandomHeroes']);
    heroServiceSpy = heroService.getRandomHeroes.and.returnValue(of(HEROES));
    await TestBed.configureTestingModule({
      imports: [RouterModule.forRoot([])],
      declarations: [ DashboardComponent, HeroSearchComponent ],
      providers: [
        {
          provide: HeroService,
          useValue: heroService
        }
      ]
    })
    .compileComponents();


  });

  beforeEach(() => {

    fixture = TestBed.createComponent(DashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should render heroes', () => {
    const elem = fixture.nativeElement as HTMLElement;
    expect(heroServiceSpy.calls.any()).withContext("Calls mock").toBeTrue();
    expect(elem.querySelectorAll('div.heroes-menu a').length).toBeGreaterThan(2);
  })

});
