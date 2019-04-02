import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { SearchBarComponent } from './search-bar/search-bar.component';
import { ResultsComponent } from './results/results.component';
import {AdminHomeComponent} from './admin-home/admin-home.component'
import { ConceptsComponent } from './concepts/concepts.component';
import { LoginComponent } from './login/login.component';
import { DisplayAdminHomeComponent } from './display-admin-home/display-admin-home.component';
import { QuestionClassifyComponent } from './question-classify/question-classify.component';
import { DomainConceptNameService } from './services/domain-concept-name.service';
import { DomainConceptComponent } from './domain-concept/domain-concept.component';
import { UploadComponent } from './upload/upload.component';
import { NodeComponent } from './neo4j-node/neo4j-node.component';
import { DisplayD3Component } from './display-d3/display-d3.component';
import { ShowNodesComponent } from './show-nodes/show-nodes.component';
import { ResultcardComponent } from './resultcard/resultcard.component';
import { WebresultcardComponent } from './webresultcard/webresultcard.component';
import { RecommendationcardComponent } from './recommendationcard/recommendationcard.component';
import { TopsearchComponent } from './topsearch/topsearch.component';
import { ToptrendingComponent } from './toptrending/toptrending.component';
import { DisplayIntentComponent } from './display-intent/display-intent.component';
import { NavigConceptComponent } from './navig-concept/navig-concept.component';
import { NavigIntentComponent } from './navig-intent/navig-intent.component';
import { ConceptNodesComponent } from './concept-nodes/concept-nodes.component';
import { IntentNodesComponent } from './intent-nodes/intent-nodes.component';
const routes: Routes = [
  {
    path:"home",
    component:HomeComponent
},
  // {
  //   path:"login",
  //   component:LoginComponent
  // },
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
},
{
  path: 'result',

  component: ResultsComponent,
  children :[
    {
      path:'resultcard',
      component: ResultcardComponent
    },
    {
      path:'webresult',
      component: WebresultcardComponent
    },{
      path:'recommendation',
      component:RecommendationcardComponent
    },{
      path:'topsearch',
      component:TopsearchComponent
    },{
      path:'toptrending',
      component:ToptrendingComponent
    }
  ]
},
{
  path: 'showD3',

  component: ShowNodesComponent
},
{
  path: 'display',

  component: DisplayD3Component
},
{
  path: 'displayConceptNodes',

  component: ConceptNodesComponent
},
{
  path: 'displayIntentNodes',

  component: IntentNodesComponent
},
{
  path: 'displayIntent',

  component: DisplayIntentComponent
},
{
  path: 'navigConcept',

  component: NavigConceptComponent
},
{
  path: 'navigIntent',

  component: NavigIntentComponent
},
{
  path:'search',

  component: SearchBarComponent
},
{
  path:'adminHome',
  component:AdminHomeComponent
},
{
  path:'displayAdminHome',
  component:DisplayAdminHomeComponent
},
{
  path:'login',
  component:LoginComponent
},
{
  path:'showNlpQuestions',
  component:QuestionClassifyComponent
},
{
  path:'addDomainConceptNames',
  component:DomainConceptComponent
},
{
  path:'uploadFile',
  component:UploadComponent
},{
  path:'modifyNeo4j',
  component:NodeComponent
}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
