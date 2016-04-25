import React, { Component, PropTypes } from 'react'
import { connect } from 'react-redux'
import { navigate, loginRequest, updateRouterState, resetErrorMessage } from '../actions'
import Explore from '../components/Explore'


class App extends Component {
  constructor(props) {
    super(props);
    this.handleChange = this.handleChange.bind(this);
    this.handleLoginChange = this.handleLoginChange.bind(this);
    this.handleDismissClick = this.handleDismissClick.bind(this);
  }

  componentWillMount() {
    this.props.updateRouterState({
      pathname: this.props.location.pathname,
      params  : this.props.params
    });
  }

  componentWillReceiveProps(nextProps) {
    if(this.props.location.pathname !== nextProps.location.pathname)
      this.props.updateRouterState({
        pathname: nextProps.location.pathname,
        params  : nextProps.params
      });
  }

  handleDismissClick(e) {
    this.props.resetErrorMessage();
    e.preventDefault();
  }

  handleChange(nextValue) {
    this.props.navigate(`/${nextValue}`);
  }

  handleLoginChange(nextValue) {
    this.props.loginRequest(nextValue);
  }

  renderErrorMessage() {
    const { errorMessage } = this.props;
    if (!errorMessage) {
      return null;
    }

    return (
      <p style={{ backgroundColor: '#e99', padding: 10 }}>
        <b>{errorMessage}</b>
        {' '}
        (<a href="#"
            onClick={this.handleDismissClick}>
        Dismiss
      </a>)
      </p>
    )
  }

  render() {
    const { children, inputValue } = this.props;
    return (
      <div>
        <Explore value={inputValue}
                 onChange={this.handleChange}
                 onLoginChange={this.handleLoginChange}/>
        <hr />
        {this.renderErrorMessage()}
        {children}
      </div>
    )
  }
}

App.propTypes = {
  // Injected by React Redux
  errorMessage: PropTypes.string,
  inputValue: PropTypes.string.isRequired,
  navigate: PropTypes.func.isRequired,
  loginRequest: PropTypes.func.isRequired,
  updateRouterState: PropTypes.func.isRequired,
  resetErrorMessage: PropTypes.func.isRequired,
  // Injected by React Router
  children: PropTypes.node
};

function mapStateToProps(state) {
  return {
    errorMessage: state.errorMessage,
    inputValue: state.router.pathname.substring(1)
  }
}

export default connect(mapStateToProps, {
  navigate,
  loginRequest,
  updateRouterState,
  resetErrorMessage
})(App);
