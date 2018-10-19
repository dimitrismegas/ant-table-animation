# ant-table-animation

A [re-frame](https://github.com/Day8/re-frame) application designed to demonstrate combination of [antizer](https://github.com/priornix/antizer) with [rc-animate](https://github.com/react-component/animate) for animated tables.
Trying to port the example from [here](http://react-component.github.io/table/examples/animation.html) but failing miserably ...at least for now

## Development Mode

### Run application:

```
lein clean
lein figwheel dev
```

Figwheel will automatically push cljs changes to the browser.

Wait a bit, then browse to [http://localhost:3449](http://localhost:3449).

## Production Build


To compile clojurescript to javascript:

```
lein clean
lein cljsbuild once min
```
