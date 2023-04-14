
import axios from 'axios'

const instance = axios.create({
  baseURL: process.env.VUE_APP_SERVER_URI,
  timeout: 2000
});

const createApi = (auth) => {

  instance.interceptors.request.use(async function (config) {
    const accessToken = await auth.getAccessTokenSilently();

    config.headers = {
      Authorization: `Bearer ${accessToken}`
    }
    return config;
  }, function (error) {
    return Promise.reject(error);
  });

  return {

    // (C)reate
    createNew(text, completed) {
      return instance.post('/api/todo', {title: text, completed: completed})
    },

    // (R)ead
    getAll() {
      return instance.get('/api/todo', {
        transformResponse: [function (data) {
          return data ? JSON.parse(data) : data;
        }]
      })
    },

    // (U)pdate
    updateForId(id, text, completed) {
      return instance.put('/api/todo/' + id, {title: text, completed: completed})
    },

    // (D)elete
    removeForId(id) {
      return instance.delete('/api/todo/' + id)
    }
  }
}

export default createApi