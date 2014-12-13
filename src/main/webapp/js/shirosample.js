$(function () {
    $('#signinForm').bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            email: {
                trigger: 'blur',
                validators: {
                    notEmpty: {
                        message: 'The email address is required and cannot be empty'
                    },
                    emailAddress: {
                        message: 'The email address is not a valid'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: 'The password is required and cannot be empty'
                    },
                    different: {
                        field: 'email',
                        message: 'The password cannot be the same as username'
                    },
                    stringLength: {
                        min: 8,
                        message: 'The password must have at least 8 characters'
                    }
                }
            }
        }
    }).on('success.form.bv', function (e) {
        e.preventDefault();
        var $form = $(e.target);

        $.ajax({
            url: $form.attr('action'),
            type: $form.attr('method'),
            data: $form.serialize(),
            dataType: 'json'
        }).done(function (res) {
            console.log('ok');
            location.reload();
        }).fail(function (jqXHR) {
            console.log(jqXHR);
            showMsgBelowForm(jqXHR.responseJSON, 'signinForm','alert-danger');

            // cleanup password
            $('#signinForm').find('input[name=password]').val("");
        });

    });

    $('#signupForm').bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            email: {
                trigger: 'blur',
                validators: {
                    notEmpty: {
                        message: 'The email address is required and cannot be empty'
                    },
                    emailAddress: {
                        message: 'The email address is not a valid'
                    },
                    remote: {
                        message: 'The email address is alreadly registered',
                        url: '/ShiroSample/users/valid'
                    }
                }
            },
            password: {
                trigger: 'blur',
                validators: {
                    notEmpty: {
                        message: 'The password is required and cannot be empty'
                    },
                    different: {
                        field: 'email',
                        message: 'The password cannot be the same as email'
                    },
                    stringLength: {
                        min: 8,
                        message: 'The password must have at least 8 characters'
                    }
                }
            },
            confirmPassword: {
                validators: {
                    notEmpty: {
                        message: 'The confirm password is required and cannot be empty'
                    },
                    identical: {
                        field: 'password',
                        message: 'The password and its confirm are not the same'
                    }
                }
            },
            firstName: {
                trigger: 'blur',
                validators: {
                    notEmpty: {
                        message: 'The FirstName is required and cannot be empty'
                    }
                }
            },
            lastName: {
                trigger: 'blur',
                validators: {
                    notEmpty: {
                        message: 'The LastName is required and cannot be empty'
                    }
                }
            },
            confirmTerm: {
                validators: {
                    notEmpty: {
                        message: 'Agree confirm Terms of Service required'
                    }
                }
            }
        }
    }).on('success.form.bv', function (e) {
        e.preventDefault();
        var $form = $(e.target);

        $.ajax({
            url: $form.attr('action'),
            type: $form.attr('method'),
            data: $form.serialize(),
            dataType: 'json'
        }).done(function (res) {
            showMsgBelowForm(res, 'signupForm','alert-info');
            // cleanup form
            $('#signupForm').data('bootstrapValidator').resetForm();
            $('#signupForm').find('input[type=text], input[name=email], input[name=password], input[name=confirmPassword]').val("");
            $('#confirmTerm').prop('checked', false);
        }).fail(function (jqXHR) {
            showMsgBelowForm(jqXHR.responseJSON, 'signupForm', 'alert-danger');
            // cleanup password
            $('#signupForm').find('input[name=password], input[name=confirmPassword]').val("");
        });
    });
    
    function showMsgBelowForm(res, formid, alertClass) {
        var $msg = $('<div/>').attr('id', formid + '-msg');
        $msg.addClass('alert').addClass(alertClass);
        $msg.append('<button class="close" data-dismiss="alert" type="button">×</button>');
        $msg.append('<strong>' + res.title + '</strong>');
        var descs = '<ul>';
        for (var desc in res.descriptions) {
            descs += '<li>' + res.descriptions[desc] + '</li>';
        }
        descs += '</ul>';
        $msg.append(descs);
        if ($('#' + formid + '-msg').length !== 0) {
            $('#' + formid + '-msg').remove();
        }
        $msg.hide().appendTo('#' + formid).fadeIn();
    }
});