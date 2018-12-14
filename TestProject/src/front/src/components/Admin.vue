<template>
  <div>
    This is Admin Page
    {{msg}}
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'Admin',
  data () {
    return {
      msg: 'test'
    }
  },
  created() {
	axios.post('http://localhost:8010/test')
	.then({
	})  
	.catch({
	})
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>