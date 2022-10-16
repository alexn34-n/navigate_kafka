<template>
  <div class="planeBlock" :style="'z-index:'+level">
    <div class="icon" :style="'left: ' + port.x + '%; top: ' + port.y + '%; transform: rotate(' + (port.angle - 180) + 'deg);'">&#9972;</div>
    <div class="title" :style="'left: ' + port.x + '%; top: ' + port.y + '%;'">{{port.name}}</div>
  </div>
</template>
<script>
export default {
  props: {
    level: 0,
    plane: null
  }
}
</script>
<style>
.planeBlock {
  width: 100%;
  min-height: 100vh;
  position: absolute;
}
.planeBlock .icon {
  position: absolute;
  font-size: 300%;
  color: #ffffff;
  transform-origin: center center;
}
.planeBlock .title {
  position: absolute;
  background: #232323;
  color: chartreuse;
  padding: 2px 5px;
  text-align: center;
}
</style>
