<p align="center"><br><img src="https://user-images.githubusercontent.com/236501/85893648-1c92e880-b7a8-11ea-926d-95355b8175c7.png" width="128" height="128" /></p>
<h3 align="center">App Icon</h3>
<p align="center"><strong><code>@capacitor-community/app-icon</code></strong></p>
<p align="center">
  Capacitor community plugin for managing an iOS app's icon. The main feature being that you can programmatically change the app icon.
</p>

<p align="center">
  <img src="https://img.shields.io/maintenance/yes/2020?style=flat-square" />
  <a href="https://github.com/capacitor-community/example/actions?query=workflow%3A%22CI%22"><img src="https://img.shields.io/github/workflow/status/capacitor-community/example/CI?style=flat-square" /></a>
  <a href="https://www.npmjs.com/package/@capacitor-community/example"><img src="https://img.shields.io/npm/l/@capacitor-community/example?style=flat-square" /></a>
<br>
  <a href="https://www.npmjs.com/package/@capacitor-community/example"><img src="https://img.shields.io/npm/dw/@capacitor-community/example?style=flat-square" /></a>
  <a href="https://www.npmjs.com/package/@capacitor-community/example"><img src="https://img.shields.io/npm/v/@capacitor-community/example?style=flat-square" /></a>
<!-- ALL-CONTRIBUTORS-BADGE:START - Do not remove or modify this section -->
<a href="#contributors-"><img src="https://img.shields.io/badge/all%20contributors-0-orange?style=flat-square" /></a>
<!-- ALL-CONTRIBUTORS-BADGE:END -->
</p>

## Maintainers

| Maintainer | GitHub | Social |
| -----------| -------| -------|
| John Borges | [johnborges](https://github.com/johnborges) | [@johnborges](https://twitter.com/johnborges) |

## Installation

```bash
npm install @capacitor-community/app-icon
npx cap sync
```

## Configuration

### Add Alternate Icons

The alternate icons need to be included within the app bundle and referenced in the iOS project `Info.plist` prior to using this plugin. It is not possible to switch to any icon on the fly without adding it to the iOS project first.

Add the alternate icons directly to your iOS project or in a subdirectory.


### Setup Info.plist

## Usage

> This plugin only changes the main app icon on the device homescreen. The icon in springboard and in other areas of iOS will not change and continue to show the original app icon.

TODO
