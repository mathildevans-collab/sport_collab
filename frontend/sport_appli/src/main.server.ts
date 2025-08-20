import { HomeComponent } from './app/components/home/home';

import { bootstrapApplication } from '@angular/platform-browser';

import { config } from './app/app.config.server';

const bootstrap = () => bootstrapApplication(HomeComponent, config);

export default bootstrap;
