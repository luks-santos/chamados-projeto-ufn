import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { authGuard } from '../guards/auth.guard';
import { MenuSidenavComponent } from './components/menu-sidenav/menu-sidenav.component';
import { CategoriesComponent } from './containers/categories/categories.component';
import { CommentsComponent } from './containers/comments/comments.component';
import { TicketsComponent } from './containers/ticket/tickets/tickets.component';
import { TopicsComponent } from './containers/topics/topics.component';


const routes: Routes = [
  {
    path: '',
    canActivateChild: [authGuard],
    component: MenuSidenavComponent,
    children: [
      { path: '', component: TicketsComponent, data: { title: 'Chamados' } },
      { path: 'categories', component: CategoriesComponent, data: { title: 'Categorias', expectedRole: 'ADMIN' } },
      { path: 'comments', component: CommentsComponent, data: { title: 'Comentários', expectedRole: 'ADMIN' } },
      { path: 'topics', component: TopicsComponent, data: { title: 'Tópicos', expectedRole: 'ADMIN' } },
    ],
  },
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ChamadosRoutingModule { }
